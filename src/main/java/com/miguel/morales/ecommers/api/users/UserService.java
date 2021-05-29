package com.miguel.morales.ecommers.api.users;

import com.miguel.morales.ecommers.api.crud.HibernateService;
import com.miguel.morales.ecommers.api.users.dto.CreateUserDto;
import com.miguel.morales.ecommers.api.users.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements HibernateService<UserModel, CreateUserDto, UpdateUserDto> {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserModel> getAll() {
        return (List<UserModel>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserModel> getOne(Long id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<UserModel> getOneByUser(String user) {
        return userRepository.findByEmail(user);
    }

    @Override
    @Transactional
    public UserModel create(CreateUserDto item) {
        return userRepository.save(item.toModel());
    }

    @Override
    @Transactional
    public UserModel update(UpdateUserDto item, Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.save(item.toModel());
        }
        return null;
    }

    @Override
    @Transactional
    public int delete(Long id) {
        try {
            userRepository.deleteById(id);
            return 1;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
