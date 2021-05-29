package com.miguel.morales.ecommers.api.imagesProducts;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesProductRepository extends CrudRepository<ImagesProductModel, Long> {
//    List<UserModel> findAllBy/(String email);
}
