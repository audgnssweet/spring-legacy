package com.audgnssweet.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogIntercepter extends HandlerInterceptorAdapter {

    //컨트롤러 메서드가 실행되기 이전
    //handler - 호출되는 메서드.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        System.out.println(handler.toString() + "가 호출되었습니다.");
        return true;
    }

    //컨트롤러 메서드가 실행된 이후
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        System.out.println(handler.toString() + "가 종료되었습니다." + modelAndView.getViewName()
        + "을 view로 사용합니다.");
    }

}
