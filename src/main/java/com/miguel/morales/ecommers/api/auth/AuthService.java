package com.miguel.morales.ecommers.api.auth;

import com.miguel.morales.ecommers.api.auth.dto.AuthRequestDto;
import com.miguel.morales.ecommers.exceptions.HttpRequestException;
import com.miguel.morales.ecommers.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthService userAuthService;

    public String signIn(AuthRequestDto signIn) {
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
        return jwtUtil.generateToken(userDetails);
    }
}
