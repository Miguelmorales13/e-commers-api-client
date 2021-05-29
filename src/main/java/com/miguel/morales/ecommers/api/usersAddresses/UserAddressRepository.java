package com.miguel.morales.ecommers.api.usersAddresses;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends CrudRepository<UserAddressModel, Long> {
}
