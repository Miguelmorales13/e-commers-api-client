package com.miguel.morales.ecommers.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    private String user;
    private String password;
}
