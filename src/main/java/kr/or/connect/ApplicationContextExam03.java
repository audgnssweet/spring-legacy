package kr.or.connect;

import kr.or.connect.beans.Car;
import kr.or.connect.config.ApplicationConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextExam03 {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = new AnnotationConfigApplicationContext(
            ApplicationConfig.class);

        Car car = configurableApplicationContext.getBean(Car.class);
        car.run();
    }

}
