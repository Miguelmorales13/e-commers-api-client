package com.miguel.morales.ecommers.api.products;

import com.miguel.morales.ecommers.api.crud.HibernateServiceImpl;
import com.miguel.morales.ecommers.api.products.dto.CreateProductDto;
import com.miguel.morales.ecommers.api.products.dto.UpdateProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService extends HibernateServiceImpl<ProductModel, CreateProductDto, UpdateProductDto, Long, ProductRepository> {


    public ProductService(@Autowired ProductRepository repository) {
        super(repository);
    }

    @Transactional(readOnly = true)
    public List<ProductModel> getAllByAny(Long categoryId) {
        return super.getRepository().findAllByCategoryId(categoryId);
    }
}
