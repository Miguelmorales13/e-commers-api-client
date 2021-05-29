package com.miguel.morales.ecommers.api.usersAddresses.dto;

import com.miguel.morales.ecommers.api.usersAddresses.UserAddressModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserAddressDto extends CreateUserAddressDto {
    private Long userId;

    public UserAddressModel toModel() {
        return new UserAddressModel(this);
    }

}
