package com.miguel.morales.ecommers.api.products;

import com.miguel.morales.ecommers.interceptors.anotations.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    @ResponseSuccess(message = "get success")
    public List<ProductModel> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{categoryId}")
    @ResponseSuccess(message = "get success")
    public List<ProductModel> getAllByCategory(@PathVariable("categoryId") String categoryId) {
        return productService.getAllByAny(Long.parseLong(categoryId));
    }
}
