package com.miguel.morales.ecommers.api.auth;

import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.api.users.UserService;
import com.miguel.morales.ecommers.exceptions.HttpRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserAuthService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserAuthService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        logger.error("the user is ".concat(userName));
        Optional<UserModel> userModel = userService.getOneByUser(userName);
        logger.error("the user is " + userModel.isPresent());

        if (userModel.isEmpty()) {
            logger.error("[The error is in User auth service because the user not exist]");
            throw new HttpRequestException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
        return new User(userModel.get().getEmail(), userModel.get().getPassword(), new ArrayList<>());
//        aqui agregamos la logica de validacion de usuario
    }
}
