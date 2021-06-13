package com.miguel.morales.ecommers.api.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
    Optional<UserModel> findFirstByEmail(String email);
//    List<UserModel> findAllBy/(String email);
}
