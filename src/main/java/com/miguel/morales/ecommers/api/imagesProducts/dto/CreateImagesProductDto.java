package com.miguel.morales.ecommers.api.imagesProducts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateImagesProductDto {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;

}
