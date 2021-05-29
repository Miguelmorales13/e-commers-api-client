package com.miguel.morales.ecommers.api.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;

}
