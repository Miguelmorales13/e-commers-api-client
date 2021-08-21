package com.miguel.morales.ecommers.api.users;

import com.miguel.morales.ecommers.api.users.dto.CreateUserDto;
import com.miguel.morales.ecommers.api.users.dto.UpdateUserDto;
import com.miguel.morales.ecommers.interceptors.anotations.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    @ResponseSuccess(message = "getter successful")
    public List<UserModel> getAll() {
        List<UserModel> users = userService.getAll();
        return users;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseSuccess(message = "Creation successful")
    public UserModel create(@Valid @RequestBody CreateUserDto user) {
        return userService.create(user);
    }

    @PatchMapping(path = "/{id}")
    @ResponseSuccess(message = "Updating successful")
    public UserModel update(@RequestBody UpdateUserDto user, @PathVariable("id") Long id) {
        return userService.update(user, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseSuccess(message = "Deleted successful")
    public int delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
