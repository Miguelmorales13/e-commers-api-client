package com.miguel.morales.ecommers.api.categories;

import com.miguel.morales.ecommers.api.crud.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/categories-products")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriesProductController {
    @Autowired
    CategoriesProductService categoriesProductService;
    @Autowired
    Generator generator;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<CategoriesProductModel> categoriesProducts = categoriesProductService.getAll();
        return generator.response(categoriesProducts, "done", HttpStatus.OK);
    }
}
