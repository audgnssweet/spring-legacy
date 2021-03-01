package com.audgnssweet.reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)	//함수에 붙일 거예요
@Retention(RetentionPolicy.RUNTIME)	//실행시점
public @interface RequestMapping {
	String value();
}
