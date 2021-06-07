package com.miguel.morales.ecommers.api.auth;

import com.miguel.morales.ecommers.api.auth.dto.AuthRequestDto;
import com.miguel.morales.ecommers.api.auth.dto.AuthResponseDto;
import com.miguel.morales.ecommers.api.crud.Generator;
import com.miguel.morales.ecommers.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> signIn(@RequestBody() AuthRequestDto signIn) throws Exception {
        final AuthResponseDto res = authService.signIn(signIn);
        return generator.response(res, "Login successful", HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody() AuthRequestDto signIn) throws Exception {
        return generator.response("token", "Login successful", HttpStatus.OK);
    }
}
