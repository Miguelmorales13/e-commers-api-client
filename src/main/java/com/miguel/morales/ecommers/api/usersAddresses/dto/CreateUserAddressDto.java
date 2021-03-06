package com.miguel.morales.ecommers.api.usersAddresses.dto;

import com.miguel.morales.ecommers.api.crud.ModelDto;
import com.miguel.morales.ecommers.api.usersAddresses.UserAddressModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserAddressDto implements ModelDto<UserAddressModel> {
    @NotEmpty(message = "The street and number is not empty")
    private String streetAndNumber;
    @NotEmpty(message = "The city is not empty")
    private String city;
    @NotEmpty(message = "The zip code is not empty")
    private String zipCode;
    @NotEmpty(message = "The state is not empty")
    private String state;
    private String lat;
    private String lng;
    @NotNull(message = "The user is not selected")

    private Long userId;

    @Override
    public UserAddressModel toModel() {
        return new UserAddressModel(this, null);
    }
}
