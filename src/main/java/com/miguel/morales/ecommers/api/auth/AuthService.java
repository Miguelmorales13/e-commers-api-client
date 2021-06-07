package com.miguel.morales.ecommers.api.auth;

import com.miguel.morales.ecommers.api.auth.dto.AuthRequestDto;
import com.miguel.morales.ecommers.api.auth.dto.AuthResponseDto;
import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.api.users.UserRepository;
import com.miguel.morales.ecommers.exceptions.HttpRequestException;
import com.miguel.morales.ecommers.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    public AuthResponseDto signIn(AuthRequestDto signIn) {
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
        final Optional<UserModel> user = userRepository.findByEmail(signIn.getUser());
        return new AuthResponseDto(token, user.get());
    }
}
