package com.miguel.morales.ecommers.api.users.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserUniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserUnique {
    String message() default "{users.isUnique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
