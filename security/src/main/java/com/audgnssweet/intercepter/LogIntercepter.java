package com.audgnssweet.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogIntercepter extends HandlerInterceptorAdapter {

    //Slf4j의 logger을 설정해주는 것.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //컨트롤러 메서드가 실행되기 이전
    //handler - 호출되는 메서드.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        logger.debug("{} 가 호출되었습니다.", handler.toString());
        return true;
    }

    //컨트롤러 메서드가 실행된 이후
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        logger
            .debug("{}가 종료되었습니다. {}를 view로 사용합니다.", handler.toString(), modelAndView.getViewName());
    }

}
