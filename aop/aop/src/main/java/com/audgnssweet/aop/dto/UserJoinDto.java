package com.audgnssweet.aop.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserJoinDto {

    @NotNull(message = "username key값이 없습니다")
    @NotEmpty(message = "username을 입력해야합니다")
    @Size(min = 5, max = 20, message = "길이가 부족하거나 초과했습니다")
    private String username;

    @NotNull(message = "password key값이 없습니다")
    @NotEmpty(message = "password를 입력해야합니다")
    private String password;

    private String phone;

}
