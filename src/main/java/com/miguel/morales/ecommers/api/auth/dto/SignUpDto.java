package com.miguel.morales.ecommers.api.auth.dto;

import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.api.users.validators.UserUnique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @NotEmpty(message = "The email is required")
    @Email(message = "The emails is not format correct")
    @UserUnique
    private String email;

    @NotEmpty(message = "The password is required")
    private String password;

    @NotEmpty(message = "The name is required")
    private String name;

    @NotEmpty
    @NotEmpty(message = "The last name is required")
    private String lastName;

    @NotEmpty(message = "The second last name is required")
    private String secondLastName;

    public UserModel toModel() {
        return new UserModel(this);
    }
}
