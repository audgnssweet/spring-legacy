package kr.or.connect.config;

import kr.or.connect.beans.Car;
import kr.or.connect.beans.Engine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
이런 방식은 언제 사용? 라이브러리 등을 사용할 때 코드 재사용을 하게되는데,
그럴 때는 프로젝트에 직접 bean을 가져와서 심어놓을 수 없으므로
이런식으로 의존성을 주입한다.
 */
@Configuration
public class ApplicationConfig {

//    @Bean
//    public Car car(Engine engine) {
//        Car car = new Car();
//        car.setEngine(engine);
//        return car;
//    }

    @Bean
    public Engine engine() {
        return new Engine();
    }
}
