package com.miguel.morales.ecommers.api.products.dto;

import com.miguel.morales.ecommers.api.crud.ModelDto;
import com.miguel.morales.ecommers.api.products.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto implements ModelDto<ProductModel> {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;

    @Override
    public ProductModel toModel() {
        return null;
    }
}
