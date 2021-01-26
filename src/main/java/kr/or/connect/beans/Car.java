package kr.or.connect.beans;

import org.springframework.stereotype.Component;

@Component
public class Car {

    private final Engine engine;

    public Car(Engine engine){
        System.out.println("car constructed");
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    //원래 componentscan방식으로 의존성을 주입하면 setter는 없어도된다.
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }

    public void run() {
        System.out.println("car run!!");
        this.engine.run();
    }
}
