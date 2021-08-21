package com.miguel.morales.ecommers.api.categories;

import com.miguel.morales.ecommers.api.categories.dto.CreateCategoriesProductDto;
import com.miguel.morales.ecommers.api.categories.dto.UpdateCategoriesProductDto;
import com.miguel.morales.ecommers.api.crud.HibernateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesProductService extends HibernateServiceImpl<CategoriesProductModel, CreateCategoriesProductDto, UpdateCategoriesProductDto, Long, CategoriesProductRepository> {

    public CategoriesProductService(@Autowired CategoriesProductRepository repository) {
        super(repository);
    }
}
