package org.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.test.config.ApplicationConfig;
import org.test.service.CalculatorService;

public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = new AnnotationConfigApplicationContext(
            ApplicationConfig.class);

        CalculatorService calculatorService = configurableApplicationContext
            .getBean(CalculatorService.class);

        System.out.println(calculatorService.plus(1,2));
    }
}
