package com.miguel.morales.ecommers.api.categories.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoriesProductDto {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;

}
