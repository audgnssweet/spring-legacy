package org.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.test.config.ApplicationConfig;


//단위 테스트를 위한 Mockito 사용
@ExtendWith(MockitoExtension.class)
class MyServiceTest {

    //@Mock이 붙은 녀석을 생성자로 주입한다.
    @InjectMocks
    private MyService myService;

    @Mock
    private CalculatorService calculatorService;

    @Test
    void execute() {
        //given
        //given method -> 가짜객체의 동작방식 지정.
        //calculatorService는 @Mock 객체이므로 가짜객체. 실제 객체를 참조하지 않으므로
        //calculatorService.plust()는 30을 return하라고 지시하는 것.
        given(calculatorService.plus(10,20)).willReturn(30);
        //when
        //내부적으로 myService.execute는 calculatorService.plus를 호출. 무조건 30을 반환.
        final int result = myService.execute(10, 20);
        //then
        //verify method는 주어진 객체가 plus를 호출한 적이 있는지 검증.
        //좀더 자세히는 plus가 int 2개 값을 전달받아 호출된 적이 있는지 검증.
        verify(calculatorService).plus(anyInt(), anyInt());
        assertEquals(result, 60);
    }
}