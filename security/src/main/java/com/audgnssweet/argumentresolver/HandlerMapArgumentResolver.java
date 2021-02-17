package com.audgnssweet.argumentresolver;

import com.audgnssweet.annotation.UserAgent;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class HandlerMapArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(UserAgent.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
        ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
        WebDataBinderFactory webDataBinderFactory) throws Exception {

        return nativeWebRequest.getHeader("user-agent");
    }

    //이 함수는 컨트롤러의 인자 개수만큼 호출이 된다.
    //인자의 정보를 파라미터로 전달. 해당 파라미터 정보에 원하는 정보가 있다면 true, 없다면 false
    //이게 true를 반환할 때만 아래의 resolveArgument 함수가 호출이 된다.
//    @Override
//    public boolean supportsParameter(MethodParameter methodParameter) {
//        return (methodParameter.getParameterType() == HeaderInfo.class);
//    }
//
//    //resolveArgument가 return한 값은 controller 의 메소드의 인자로 전달이 된다.
//    @Override
//    public Object resolveArgument(MethodParameter methodParameter,
//        ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
//        WebDataBinderFactory webDataBinderFactory) throws Exception {
//        HeaderInfo headerInfo = new HeaderInfo();
//
//        final Iterator<String> headerNames = nativeWebRequest.getHeaderNames();
//        while (headerNames.hasNext()) {
//            final String headerName = headerNames.next();
//            final String headerValue = nativeWebRequest.getHeader(headerName);
//
//            System.out.println(headerName + " , " + headerValue);
//            headerInfo.put(headerName, headerValue);
//        }
//        return headerInfo;
//    }
}
