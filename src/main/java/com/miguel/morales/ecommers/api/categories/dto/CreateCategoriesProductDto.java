package com.miguel.morales.ecommers.api.categories.dto;

import com.miguel.morales.ecommers.api.categories.CategoriesProductModel;
import com.miguel.morales.ecommers.api.crud.ModelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoriesProductDto implements ModelDto<CategoriesProductModel> {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;

    @Override
    public CategoriesProductModel toModel() {
        return null;
    }
}
