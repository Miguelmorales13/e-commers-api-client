package com.miguel.morales.ecommers.api.categories;

import com.miguel.morales.ecommers.interceptors.anotations.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping()
    @ResponseSuccess(message = "get success")
    public List<CategoriesProductModel> getAll() {
        return categoriesProductService.getAll();
    }
}
