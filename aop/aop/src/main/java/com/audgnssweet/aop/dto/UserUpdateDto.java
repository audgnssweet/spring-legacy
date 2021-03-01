package com.audgnssweet.aop.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateDto {

    @NotNull(message = "password key값이 없습니다")
    @NotEmpty(message = "password를 입력해야합니다")
    private String password;

    private String phone;
}
