package com.audgnssweet.aop.controller;

import com.audgnssweet.aop.common.ResponseDto;
import com.audgnssweet.aop.dto.UserJoinDto;
import com.audgnssweet.aop.dto.UserUpdateDto;
import com.audgnssweet.aop.model.User;
import com.audgnssweet.aop.repository.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
AOP - 관점 지향 프로그래밍
point cut. 내가 필요한 것들에만 집중한다.
핵심 기능과 부가 기능을 나누어서
부가 기능은 신경쓰지않는다.
AOP 라이브러리들을 이용한다.
기존의 filter + reflection으로 함수 앞 뒤를 처리했는데 언제 사용?
filter - 앞만 처리
AOP - 앞뒤처리 사용하면 된다.
filter + reflection = AOP. 스프링이 제공하는 filter + reflection이 AOP이다.

AOP와 filter의 가장 큰 차이는, 시점이다.
filter는 성에 들어오기 전. AOP는 들어와서거르는 것.

그냥 전체 전처리 할 거면 filter쓰고,
함수 앞단 뒷단 개별처리 할거면 AOP 쓰는 것이 좋음.

공부 방식
1. validation check -> AOP에 적용
2. if문 check X. spring reflection이 제공하는 어노테이션 활용.
 */


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    //Rest API 는 설계시 모델중심

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<User>> findAll() {
        return new ResponseDto<>(HttpStatus.OK,
            userRepository.findAll());    // MessageConverter가 동작. JavaObject -> json String data.
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<User> findById(@PathVariable Integer id) {
        return new ResponseDto<>(HttpStatus.OK, userRepository.findById(id));
    }

    //스프링은기본적으로 reflection동작
    //아무어노테이션도안붙여주면
    //x-www-form-urlencoded 형식으로 동작 -> request.getParameter() 가 동작해서 자동매칭.
    //text/plain -> @RequestBody 어노테이션을 이용해서 읽는다.
    //application/json -> @RequestBody + 객체로 받아야함 그래야 파싱해줌.
    //ResponseEntity - spring에서 기본제공하는 response entity
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    //wildcard ? - runtime시에 결정하겠다.
    public ResponseDto<?> save(
//        String username, String password, String phone
        @Valid @RequestBody UserJoinDto userJoinDto,
        BindingResult bindingResult
    ) {
        //userJoinDto를 알아보니 error가 존재 할 때만
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorMap = new HashMap<>();
//
//            for (FieldError error : bindingResult.getFieldErrors()) {
//                //어떤 필드에서 오류가 났는지, 내가 정해놓은 message가 전달.
//                errorMap.put(error.getField(), error.getDefaultMessage());
//            }
//            //정상실행 - User을 return, 정상 실행 x - map return.
//            //이럴 때 type에 wildcard를 써준다.
//            return new ResponseDto<>(HttpStatus.BAD_REQUEST, errorMap);
//        }

        //이것처럼 updateDto에도 똑같이 추가하고 controller의 함수에도 추가를 해줘야 한다.
        //이 함수에서 핵심 로직은 아래. 공통 기능은 위에인데 공통 기능이 반복된다. 이를 AOP로 빼내야 하는 것.

        final User savedUser = userRepository.save(userJoinDto);
        System.out.println("save 함수 실행중");
        return new ResponseDto<>(HttpStatus.CREATED, savedUser);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<String> deleteById(@PathVariable Integer id) {
        return new ResponseDto<>(HttpStatus.OK, userRepository.deleteById(id));
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<String> updateById(
        @PathVariable Integer id,
        @Valid @RequestBody UserUpdateDto userUpdateDto,
        BindingResult bindingResult
    ) {
        return new ResponseDto<>(HttpStatus.OK, userRepository.updateById(id, userUpdateDto));
    }

}
