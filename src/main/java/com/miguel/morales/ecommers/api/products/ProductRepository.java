package com.miguel.morales.ecommers.api.products;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Long> {
    //    List<UserModel> findAllBy/(String email);
    List<ProductModel> findAllByCategoryId(Long categoryId);
}
