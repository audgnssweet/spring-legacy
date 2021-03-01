package com.audgnssweet.reflect.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.audgnssweet.reflect.annotation.RequestMapping;
import com.audgnssweet.reflect.controller.UserController;

public class Dispatcher implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String name = req.getRequestURI().replace(req.getContextPath(), "");
//		System.out.println(name);
		
		UserController userController = new UserController();
		
		//이런식으로 함수가 많아질수록 점점 유지보수가 힘들어진다.
//		if(name.equals("/user")) {
//			userController.user();
//		}else if(name.equals("/login")) {
//			userController.login();
//		}else if(name.equals("/join")) {
//			userController.join();
//		}
		
		//그 파일에만 있는 메서드 (상속받는 것들 제외) 하고 return해줌.
		//이런 방식이 reflection -> 런타임시점에 메서드를 찾아내서 실행해줌. (분기)
		Method[] methods = userController.getClass().getDeclaredMethods();
//		for(Method method : methods) {
////			System.out.println(method.getName());
//			if(name.equals("/" + method.getName())) {
//				try {
//					method.invoke(userController);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		
		//파라미터에 값이 없을 때
//		for(Method method : methods) {
//			RequestMapping annotation = method.getAnnotation(RequestMapping.class);
//			if(annotation.value().equals(name)) {
//				try {
//					String path = (String) method.invoke(userController);
//					
//					//send:redirect VS requestDispatcher
//					//만드는사람입장에서는 무조건 requestDispatcher가좋음 왜?
//					//request를 가지고 넘어가야하는 경우도 있기 때문에 -> redirect말고 forward
//					//requestdispatcher에 /만 넣어줬는데 어떻게 index를 찾아갈까?
//					//requestdispatcher는 필터를 안타기 때문. 내부에서 실행되니까.
//					//forward자체가 redirect방식이 아니기때문에 필터를 다시 안탐.
//					RequestDispatcher dispatcher = req.getRequestDispatcher(path);
//					dispatcher.forward(req, resp);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				break;
//			}
//		}
		
		boolean isRight = false;
		//파라미터가 있을 때
		for(Method method : methods) {
			RequestMapping annotation = method.getAnnotation(RequestMapping.class);
			if(annotation.value().equals(name)) {
				try {
					Parameter[] params = method.getParameters();
					String path = null;
					//parameter가 있는지 확인
					if(params.length != 0) {
						//있으면 어떤 type인지 확인해야함.
						//첫 번째 파라미터의 타입의 instance를 만들어서 dtoInstance에 넣어줌.
						Object dtoInstance = params[0].getType().newInstance();
						//어떤 함수가 호출되냐에따라 어떤 dto가 생성될지 결정
						//-> runtime시 결정 -> 리플렉션
						//해당객체가갖는 함수를 다 받아와서 setter을 호출해주면된다.
//						Enumeration<String> parameterNames = req.getParameterNames();
//						while(parameterNames.hasMoreElements()) {
//							String nextElement = parameterNames.nextElement();
//							System.out.println(nextElement);
//						}
//						이 녀석들을 setter형식으로 만들어주면돼.
						setData(dtoInstance, req);
						method.invoke(userController, dtoInstance);
						
//						System.out.println("params[0] : " + params[0]);
//						System.out.println("params[0].getName() : " + params[0].getName());
//						System.out.println("params[0].getType() : " + params[0].getType());
						
//						System.out.println("username : " + req.getParameter("username"));
//						System.out.println("password : " + req.getParameter("password"));
						
						path = "/";
					}else {
						//없으면 그냥 호출
						path = (String) method.invoke(userController);
					}
					
					//send:redirect VS requestDispatcher
					//만드는사람입장에서는 무조건 requestDispatcher가좋음 왜?
					//request를 가지고 넘어가야하는 경우도 있기 때문에 -> redirect말고 forward
					//requestdispatcher에 /만 넣어줬는데 어떻게 index를 찾아갈까?
					//requestdispatcher는 필터를 안타기 때문. 내부에서 실행되니까.
					//forward자체가 redirect방식이 아니기때문에 필터를 다시 안탐.
					RequestDispatcher dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
				isRight = true;
				break;
			}
		}
		if(!isRight) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter writer = resp.getWriter();
			writer.print("잘못된 주소 요청입니다");
		}
		
		//이처럼 함수의 호출전과 후의 관리 가능.
		//AOP가 가능해진다.
		//함수가 추가되어도 reflection 내에서 수정할 것이 없음.
	}

	private <T> void setData(T dtoInstance, HttpServletRequest req) throws Exception {
		Enumeration<String> parameterNames = req.getParameterNames();
		Method[] methods = dtoInstance.getClass().getDeclaredMethods();
		
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String setterName = paramToSetter(parameterName);
			
			for(Method method : methods) {
				if(setterName.equals(method.getName())) {
					String parameter = req.getParameter(parameterName);
					//1. 파라미터전체가숫자인지
					//2. 이름으로(id) 확인
					//3. isDigit 사용
					boolean isNumber = checkNumber(parameter);
					if(isNumber) {
						method.invoke(dtoInstance, Integer.valueOf(parameter));
					}else {
						method.invoke(dtoInstance, parameter);
					}
				}
			}
		}
	}

	private boolean checkNumber(String parameter) {
		try {
			Integer.parseInt(parameter);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	private String paramToSetter(String parameterName) {
		return "set" + parameterName.substring(0, 1).toUpperCase() + parameterName.substring(1);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy 호출");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init 호출");
	}


}
