package com.miguel.morales.ecommers.api.auth.dto;

import com.miguel.morales.ecommers.api.users.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    private String token;
    private UserModel user;
}
