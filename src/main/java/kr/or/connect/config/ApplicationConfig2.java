/*
의존성 주입의 3가지 방식.
필드 주입, 생성자 주입, setter 주입
생성자 주입이 권장된다.
 */

package kr.or.connect.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration                          //이것은 configuration입니다.
@ComponentScan("kr.or.connect.beans")   //지정된 범위에서 자동으로 bean을 scan하라고 명령
public class ApplicationConfig2 {

}
