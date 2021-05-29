package com.miguel.morales.ecommers.api.users;

import com.miguel.morales.ecommers.api.crud.Generator;
import com.miguel.morales.ecommers.api.users.dto.CreateUserDto;
import com.miguel.morales.ecommers.api.users.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    Generator generator;


    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<UserModel> users = userService.getAll();
        return generator.response(users, "done", HttpStatus.OK);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel create(@Valid @RequestBody CreateUserDto user) {
        return userService.create(user);
    }

    @PatchMapping(path = "/{id}")
    public UserModel update(@RequestBody UpdateUserDto user, @PathVariable("id") Long id) {
        return userService.update(user, id);
    }

    @DeleteMapping(path = "/{id}")
    public int delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
