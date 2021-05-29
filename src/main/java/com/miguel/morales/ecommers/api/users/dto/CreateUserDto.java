package com.miguel.morales.ecommers.api.users.dto;

import com.miguel.morales.ecommers.api.users.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;

    public UserModel toModel() {
        return new UserModel(this);
    }
}
