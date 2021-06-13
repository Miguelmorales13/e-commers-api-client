package com.miguel.morales.ecommers.api.auth;

import com.miguel.morales.ecommers.api.auth.dto.SignInDto;
import com.miguel.morales.ecommers.api.auth.dto.SignInResponseDto;
import com.miguel.morales.ecommers.api.auth.dto.SignUpDto;
import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.api.users.UserRepository;
import com.miguel.morales.ecommers.exceptions.HttpRequestException;
import com.miguel.morales.ecommers.utils.EmailUtil;
import com.miguel.morales.ecommers.utils.JWTUtil;
import com.miguel.morales.ecommers.utils.enums.TemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailUtil emailUtil;

    public SignInResponseDto signIn(SignInDto signIn) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signIn.getUser(),
                            signIn.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new HttpRequestException("Credentials invalids");
        }
        final UserDetails userDetails = userAuthService.loadUserByUsername(signIn.getUser());
        final String token = jwtUtil.generateToken(userDetails);
        final Optional<UserModel> user = userRepository.findFirstByEmail(signIn.getUser());
        return new SignInResponseDto(token, user.get());
    }

    public UserModel singUp(SignUpDto signUp) throws IllegalAccessException {
        final UserModel userCreated = this.userRepository.save(signUp.toModel());
        final Map<String, Object> res = userCreated.toMap();
        res.put("link", "http://localhost:8080/auth/verification");
        emailUtil.sendEmail(signUp.getEmail(), "subscription", res, TemplateType.ConfirmEmail);
        return userCreated;
    }
}
