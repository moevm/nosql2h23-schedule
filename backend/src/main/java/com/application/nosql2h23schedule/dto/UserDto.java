package com.application.nosql2h23schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String token_access;
    private String email;
    private String password;
    private String fullName;
    private String role;
}
