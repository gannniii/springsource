package com.example.board.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MemberDTO {

    @Email(message = "이메일 형식을 확인해 주세요")
    @NotBlank(message = "이메일은 필수 요소입니다")
    private String email;
    @NotBlank(message = "이름은 필수 요소입니다")
    private String name;
    @NotBlank(message = "비밀번호는 필수 요소입니다")
    private String password;

    private boolean fromSocial;

}
