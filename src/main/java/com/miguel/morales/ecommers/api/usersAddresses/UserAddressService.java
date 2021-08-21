package com.miguel.morales.ecommers.api.usersAddresses;

import com.miguel.morales.ecommers.api.crud.HibernateServiceImpl;
import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.api.users.UserRepository;
import com.miguel.morales.ecommers.api.usersAddresses.dto.CreateUserAddressDto;
import com.miguel.morales.ecommers.api.usersAddresses.dto.UpdateUserAddressDto;
import com.miguel.morales.ecommers.exceptions.HttpRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserAddressService extends HibernateServiceImpl<UserAddressModel, CreateUserAddressDto, UpdateUserAddressDto, Long, UserAddressRepository> {
    @Autowired
    private UserRepository userRepository;


    public UserAddressService(@Autowired UserAddressRepository repository) {
        super(repository);
    }

    @Transactional
    public UserAddressModel create(CreateUserAddressDto item) {
        final Optional<UserModel> user = userRepository.findById(item.getUserId());
        if (user.isEmpty()) {
            throw new HttpRequestException("User invalid");
        }
        return super.create(item);
    }
}
