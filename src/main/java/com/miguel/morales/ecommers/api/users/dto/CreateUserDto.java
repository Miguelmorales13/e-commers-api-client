package com.miguel.morales.ecommers.api.users.dto;

import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.api.users.validators.UserUnique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String secondLastName;
    @NotEmpty
    @UserUnique
    private String email;
    @NotEmpty
    private String password;

    public UserModel toModel() {
        return new UserModel(this);
    }
}
