package com.miguel.morales.ecommers.api.crud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Generator {
    public ResponseEntity<?> response(Object data, String message, HttpStatus code) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("message", message);
        return new ResponseEntity<>(res, code);
    }

    public ResponseEntity<?> error(String message, HttpStatus code) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("message", message);
        return new ResponseEntity<>(res, code);
    }

}
