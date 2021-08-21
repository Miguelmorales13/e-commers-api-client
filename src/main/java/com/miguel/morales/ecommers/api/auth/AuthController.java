package com.miguel.morales.ecommers.api.auth;

import com.miguel.morales.ecommers.api.auth.dto.SignInDto;
import com.miguel.morales.ecommers.api.auth.dto.SignInResponseDto;
import com.miguel.morales.ecommers.api.auth.dto.SignUpDto;
import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.interceptors.anotations.ResponseSuccess;
import com.miguel.morales.ecommers.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {


    @Autowired
    private AuthService authService;


    @Autowired
    private EmailUtil emailUtil;


    @PostMapping("/sign-in")
    @ResponseSuccess(message = "Login successful")
    public SignInResponseDto signIn(@Valid @RequestBody() SignInDto signIn) throws Exception {
        return authService.signIn(signIn);
    }

    @PostMapping("/sign-up")
    @ResponseSuccess(message = "Your register is successful but now you need confirm your email")
    public UserModel signUp(@Valid @RequestBody() SignUpDto signUp) throws Exception {
        final UserModel user = authService.singUp(signUp);
        return user;
    }
}
