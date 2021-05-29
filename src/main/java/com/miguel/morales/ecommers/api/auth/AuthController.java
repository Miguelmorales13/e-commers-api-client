package com.miguel.morales.ecommers.api.auth;

import com.miguel.morales.ecommers.api.auth.dto.AuthRequestDto;
import com.miguel.morales.ecommers.api.crud.Generator;
import com.miguel.morales.ecommers.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private Generator generator;

    @Autowired
    private AuthService authService;


    @Autowired
    private EmailUtil emailUtil;


    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody() AuthRequestDto signIn) throws Exception {
        final String token = authService.signIn(signIn);
        return generator.response(token, "Login successful", HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody() AuthRequestDto signIn) throws Exception {
        final String token = authService.signIn(signIn);
        Map<String, Object> model = new HashMap<>();
        model.put("name", "Miguel angel morales");
        model.put("subject", "Bienvenido");
        model.put("link", "http://localhost:8080/users");
        emailUtil.sendEmail("cacahuatisimo13@gmail.com", "Bienvenido", model);
        return generator.response(token, "Login successful", HttpStatus.OK);
    }
}
