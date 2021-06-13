package com.miguel.morales.ecommers.api.users.validators;

import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.api.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserUniqueValidator implements ConstraintValidator<UserUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String i, ConstraintValidatorContext constraintValidatorContext) {
        final Optional<UserModel> wanted = userRepository.findFirstByEmail(i);
        final boolean valid = wanted.isEmpty();
        return valid == true;
    }
}
