package com.miguel.morales.ecommers.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class HttpRequestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestExceptionHandler.class);

    @ExceptionHandler(value = {HttpRequestException.class})
    public ResponseEntity<HttpException> handlerError(HttpRequestException e) {
        HttpException z = new HttpException(e.getMessage(), e.getStatus().value(), ZonedDateTime.now(ZoneId.of("Z")));
        logger.error(z.toString());
        return new ResponseEntity<HttpException>(z, e.getStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<HttpException> handlerErrorValidation(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce("", (String field, String error) -> field.concat(error).concat(", ")).toString();
        HttpException z = new HttpException(message, HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now(ZoneId.of("Z")));
        logger.error(z.toString());
        return new ResponseEntity<HttpException>(z, HttpStatus.BAD_REQUEST);
    }
}
