package com.miguel.morales.ecommers.api.categories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesProductRepository extends CrudRepository<CategoriesProductModel, Long> {
    List<CategoriesProductModel> findAllByNivel(int nivel);
}
