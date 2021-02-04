package kr.or.connect;

import kr.or.connect.beans.Car;
import kr.or.connect.beans.Engine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam02 {

    public static void main(String[] args) {
        //ioc와 di를 이용하지 않았을 때
//        Car car = new Car();
//        Engine engine = new Engine();
//
//        car.setEngine(engine);
//        car.run();

        //ioc와 di를 이용했을 때
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            "classpath:applicationContext.xml");
        Car car = (Car) applicationContext.getBean("car");
        car.run();
    }
}
