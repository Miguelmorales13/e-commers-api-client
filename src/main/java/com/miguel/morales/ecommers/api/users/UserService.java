package com.miguel.morales.ecommers.api.users;

import com.miguel.morales.ecommers.api.crud.HibernateServiceImpl;
import com.miguel.morales.ecommers.api.users.dto.CreateUserDto;
import com.miguel.morales.ecommers.api.users.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends HibernateServiceImpl<UserModel, CreateUserDto, UpdateUserDto, Long, UserRepository> {

    public UserService(@Autowired UserRepository repository) {
        super(repository);
    }

    @Transactional(readOnly = true)
    public Optional<UserModel> getOneByUser(String user) {
        return super.getRepository().findFirstByEmail(user);
    }

}
