package com.audgnssweet.aop.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto<T> {

    private Integer status;
    private T data;

    public ResponseDto(HttpStatus httpStatus) {
        this.status = httpStatus.value();
    }

    public ResponseDto(HttpStatus httpStatus, T data) {
        this(httpStatus);
        this.data = data;
    }
}
