package com.audgnssweet.aop.aop;

import com.audgnssweet.aop.common.ResponseDto;
import io.sentry.Sentry;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

//설정 외엔 Component로 뜬다 생각
//Component는 Controller가 메모리에 뜬 이후에 뜨기 때문에 적절하다.
//Advice 파일을 여러개 만들어서 다양한 처리를 할 수 있도록 한다.
@Component
@Aspect // 이녀석은 AOP야.
public class BindingAdvice {

    //로그 남기기 Slf4j 의 Logger. springboot - logback 기본사용.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //앞 - @Before   (ex. username이 없으면? 내가넣어서그냥 함수 실행시키고 싶을 때)
    //뒤 - @After    (응답만 관리하고 싶을 때)
    //둘다 - @Around  (둘 다)

    //만약 여기서 @Before로 설정하면? proceedingJoinPoint를 함수 내에서 호출이 불가능.
    //어차피 전처리만 할 거기 때문에 JoinPoint를 reflection 해 줄 이유가 없다.
    //또한 @Before하면 return 값을 활용할 수 없기 때문에, 의미가없음.
    //오류가 떠서 에러를 반환 하더라도 함수는 그냥 실행되어버린다.
    //우선순위는 Around가 먼저 낚아채고, 그 다음 Before인듯.
    @Before(value = "execution(* com.audgnssweet.aop.controller..*Controller.*(..))")
    public void logging() {
        //request값 처리는 못하나?
        //1. @Around인 경우 - Controller단에서 argument로 선언하여 proceedingJoinPoint로 넘겨받기
        //2. @Around가 아닌 경우 - RequestContextHolder 이용해서 넘겨받기.
//        final HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder
//            .currentRequestAttributes())).getRequest();
        //log는 어떻게 남기나?
//        System.out.println("전처리 로그를 남겼습니다");
    }

    @After(value = "execution(* com.audgnssweet.aop.controller..*Controller.*(..))")
    public void after() {
//        System.out.println("후처리 로그를 남겼습니다");
    }

    //value 안에 있는 함수들을 낚아 채서, 전처리와 후처리를 하겠다.
    //value 안에는 정규표현식이 들어가야 한다.
    //com.audgnssweet.aop.controller안에 Controller로 끝나는 모든 클래스의 모든 함수들 (인자 개수 상관 없이 모두) 에 적용.
    @Around(value = "execution(* com.audgnssweet.aop.controller..*Controller.*(..))")
    public Object checkValidation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //여기서 JoinPoint는 ? Advice가 적용될 위치. 메서드 호출, 예외 발생 등 특정 작업이 시작되는 시점을 말한다.
        //결국 proceedingJoinPoint는 reflection된 method를 의미한다.
        final String typeName = proceedingJoinPoint.getSignature().getDeclaringTypeName();  //클래스명
        final String method = proceedingJoinPoint.getSignature().getName(); //함수이름

//        System.out.println(typeName);
//        System.out.println(method);
        //필터와같이 여기서 멈춰버린다. Around기 때문에 다시 함수를 타게 할 것인지 아닌지를 결정해야 한다.

        //그렇다면? 얘는 reflection 된 함수고, getArgs하면 매개변수들을 모두 받을 수 있다.
        final Object[] args = proceedingJoinPoint.getArgs();

        //정상적인 요청이면 에러가 날 수가 없음.
        //프론트단에서도 에러 처리를 하기 때문.
        //postman이라던가 그런거로 날렸거나 했을 때 오류 발생.
        //해당 기록을 log로 남겨야함.
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        //어떤 필드에서 오류가 났는지, 내가 정해놓은 message가 전달.
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        //로그레벨. 개발 단계에서는 보통 debug레벨로 띄운다.
                        //error, warn, info, debug, trace 순으로 단계.
                        //로그레벨 설정을 어떻게 하냐에 따라서 왼쪽만 뜬다. 현재는 log level이 info로 되어있는 듯.
                        //이것을 파일로 남기려면 logback-spring.xml 파일을 만들어서 설정하거나,
                        //DB에 연결해서 DB에 저장해도 된다.
                        //여기 입력하는 것이 logback-spring.xml에 $msg로 들어간다
                        logger.warn(
                            typeName + "." + method + "() => 필드 : " + error.getField() + ", 메시지 : "
                                + error.getDefaultMessage()
                        );
                        //sentry.io로 보내는 로그
                        Sentry.captureMessage(
                            typeName + "." + method + "() => 필드 : " + error.getField() + ", 메시지 : "
                                + error.getDefaultMessage());
                    }
                    //정상실행 - User을 return, 정상 실행 x - map return.
                    //이럴 때 type에 wildcard를 써준다.
                    return new ResponseDto<>(HttpStatus.BAD_REQUEST, errorMap);
                }
            }
        }

        //만약 오류가 없다면? 함수 스택을 계속 타라.
        return proceedingJoinPoint.proceed();
    }
}
