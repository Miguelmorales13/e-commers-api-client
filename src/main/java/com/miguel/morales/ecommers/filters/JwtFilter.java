package com.miguel.morales.ecommers.filters;

import com.miguel.morales.ecommers.api.auth.UserAuthService;
import com.miguel.morales.ecommers.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;


    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        String token = null;
        String userName = null;

        if (null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtUtil.getUsernameFromToken(token);
        }

        if (null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userAuthService.loadUserByUsername(userName);
                if (jwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                    );

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (Exception e) {
                logger.error("[The error in filter because exist error in  un load user by username]");
            }


        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
