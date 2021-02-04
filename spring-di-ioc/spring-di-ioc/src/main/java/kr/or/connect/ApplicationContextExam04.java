package kr.or.connect;

import kr.or.connect.beans.Car;
import kr.or.connect.config.ApplicationConfig2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextExam04 {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = new AnnotationConfigApplicationContext(
            ApplicationConfig2.class);

        Car car = configurableApplicationContext.getBean(Car.class);
        car.run();
    }

}
