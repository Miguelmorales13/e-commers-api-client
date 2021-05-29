package com.miguel.morales.ecommers.api.usersAddresses;

import com.miguel.morales.ecommers.api.crud.HibernateService;
import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.api.users.UserRepository;
import com.miguel.morales.ecommers.api.usersAddresses.dto.CreateUserAddressDto;
import com.miguel.morales.ecommers.api.usersAddresses.dto.UpdateUserAddressDto;
import com.miguel.morales.ecommers.exceptions.HttpRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressService implements HibernateService<UserAddressModel, CreateUserAddressDto, UpdateUserAddressDto> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserAddressModel> getAll() {
        return (List<UserAddressModel>) userAddressRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserAddressModel> getOne(Long id) {
        return userAddressRepository.findById(id);
    }

    @Override
    @Transactional
    public UserAddressModel create(CreateUserAddressDto item) {
        final Optional<UserModel> user = userRepository.findById(item.getUserId());
        if (user.isEmpty()) {
            throw new HttpRequestException("User invalid");
        }
        return userAddressRepository.save(item.toModel(user.get()));
    }

    @Override
    @Transactional
    public UserAddressModel update(UpdateUserAddressDto item, Long id) {
        if (userAddressRepository.findById(id).isPresent()) {
            return userAddressRepository.save(item.toModel());
        }
        return null;
    }

    @Override
    @Transactional
    public int delete(Long id) {
        try {
            userAddressRepository.deleteById(id);
            return 1;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
