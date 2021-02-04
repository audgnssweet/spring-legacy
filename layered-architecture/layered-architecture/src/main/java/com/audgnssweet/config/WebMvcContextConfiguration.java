package com.audgnssweet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//이 설정들은 전부 dispatcherServlet이 읽어들이는 것
//WebMvcConfig는 controller관련 설정만 관리. (presentation단이니까)
//설정의 분리로 backend는 다른 platform에서도 사용할 수 있도록.
//web에 종속적임.

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.audgnssweet.controller"})
public class WebMvcContextConfiguration implements WebMvcConfigurer {

    //view를 보여줄 때는 이 설정으로 보여줘라.
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    //mapping 정보가 없는 url이 들어왔을 때에 spring의 defaultservlethttprequest가 대처할 수 있도록.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //특정 url에 대한 처리는 controller를 작성하지 않고 사용하기 위함.
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/")
            .setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/")
            .setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
}
