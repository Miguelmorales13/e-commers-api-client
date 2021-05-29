package com.miguel.morales.ecommers.api.products;

import com.miguel.morales.ecommers.api.crud.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    Generator generator;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<ProductModel> products = productService.getAll();
        return generator.response(products, "done", HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getAllByCategory(@PathVariable("categoryId") String categoryId) {
        List<ProductModel> products = productService.getAllByAny(Long.parseLong(categoryId));
        return generator.response(products, "done", HttpStatus.OK);
    }
}
