package com.hurrypizza.digda.api.v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class UserRegisterRequest {

    @Email
    private String email;

    @NotEmpty(message = "필수 입력 항목입니다.")
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9@$!%*#?&]{8,20}$",
            message = "비밀번호는 영문/숫자 를 꼭 포함하여 8~20자리로 입력해 주세요.")
    private String password;
    private String color;

}
