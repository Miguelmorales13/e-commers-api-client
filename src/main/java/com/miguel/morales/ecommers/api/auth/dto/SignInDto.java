package com.miguel.morales.ecommers.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
    @NotEmpty(message = "The user is required")
    private String user;
    @NotEmpty(message = "The password is required")
    private String password;
}
