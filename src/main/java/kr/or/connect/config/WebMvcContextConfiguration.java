package kr.or.connect.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
dispatcher servlet을 세팅해준 것.
이것으로 끝이 아니라 front servlet으로 등록해 주어야 한다.
 */
@Configuration
@EnableWebMvc   //이 어노테이션을 이용하면 기본적인 부분들이 세팅 되지만 필요한 경우에는,
@ComponentScan(basePackages = {"kr.or.connect.controller"})
public class WebMvcContextConfiguration implements WebMvcConfigurer {

    /*
    이런 형식의 pathPattern들이 들어오면 controller에서 찾지 말고 내가 정해준 곳에서 찾아라.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
            .setCachePeriod(31556926);

        registry.addResourceHandler("/css/**").addResourceLocations("/css/")
            .setCachePeriod(31556926);

        registry.addResourceHandler("/img/**").addResourceLocations("/img/")
            .setCachePeriod(31556926);

        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }

    /*
    DefaultServletHandler을 사용하도록 설정.
    매핑 정보가 없는 url은 default servlet http request handler가 처리하도록 해준다.
    WAS가 default servlet을 통해 static한 자원을 읽어서 보여주도록 해준다.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*
    특정 url에 대한 처리를 controller 클래스를 작성하지 않고 mapping 할 수 있도록 해준다.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*
        /로 요청이 들어오면 main이라고 하는 이름의 view로 보여줘라.
         */
        registry.addViewController("/").setViewName("main");
    }

    /*
    view 정보를 해당 설정된 형태에서 찾아라.
    view는 jsp파일이고, 해당 경로 안에다가 놓을거다.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
}
