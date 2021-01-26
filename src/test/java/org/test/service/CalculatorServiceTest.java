package org.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.test.config.ApplicationConfig;

//통합 테스트
@SpringJUnitConfig(ApplicationConfig.class)
class CalculatorServiceTest {

    private final CalculatorService calculatorService;

    @Autowired
    CalculatorServiceTest(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Test
    void plus() {
        //given
        int v1 = 10;
        int v2 = 20;
        //when
        final int result = calculatorService.plus(v1, v2);
        //then
        assertEquals(result, 30);
    }

    @Test
    void divideExceptionTest() {
        //given
        int v1 = 10;
        int v2 = 0;
        //when
        //then
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(v1, v2));
    }
}