package com.miguel.morales.ecommers.api.imagesProducts.dto;

import com.miguel.morales.ecommers.api.crud.ModelDto;
import com.miguel.morales.ecommers.api.imagesProducts.ImagesProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateImagesProductDto implements ModelDto<ImagesProductModel> {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;

    @Override
    public ImagesProductModel toModel() {
        return null;
    }
}
