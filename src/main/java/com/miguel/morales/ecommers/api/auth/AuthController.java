package com.miguel.morales.ecommers.api.auth;

import com.miguel.morales.ecommers.api.auth.dto.SignInDto;
import com.miguel.morales.ecommers.api.auth.dto.SignInResponseDto;
import com.miguel.morales.ecommers.api.auth.dto.SignUpDto;
import com.miguel.morales.ecommers.api.crud.Generator;
import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {


    @Autowired
    private Generator generator;

    @Autowired
    private AuthService authService;


    @Autowired
    private EmailUtil emailUtil;


    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody() SignInDto signIn) throws Exception {
        final SignInResponseDto res = authService.signIn(signIn);
        return generator.response(res, "Login successful", HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody() SignUpDto signUp) throws Exception {
        final UserModel user = authService.singUp(signUp);
        return generator.response(user, "Your register is successful but now you need confirm your email", HttpStatus.OK);
    }
}
