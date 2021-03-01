package com.audgnssweet.reflect.controller;

import com.audgnssweet.reflect.annotation.RequestMapping;
import com.audgnssweet.reflect.dto.JoinDto;
import com.audgnssweet.reflect.dto.LoginDto;
import com.audgnssweet.reflect.entity.User;

public class UserController {
	
	//entity를 그대로 파라미터로 사용하게되면, null값이 반드시 생기게 되고, 그렇게 되면
	//validation check가 어려워진다. 통신을 위한 dto를 만드는 것이 좋음.
	//또한 reflection 할 때 유리하다.
	@RequestMapping(value = "/login")
	public String login(LoginDto loginDto) {	//username, password 필요
		System.out.println("login 호출");
		System.out.println(loginDto);
		return "/";
	}
	
	@RequestMapping(value = "/join")
	public String join(JoinDto joinDto) {	//username, password, email 필요
		System.out.println("join 호출");
		System.out.println(joinDto);
		return "/";
	}
	
	@RequestMapping(value = "/userDetail")
	public String getUserDetail(User user) {
		System.out.println("getUserDetail 호출");
		System.out.println(user);
		return "/";
	}

}
