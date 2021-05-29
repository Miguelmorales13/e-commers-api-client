package com.miguel.morales.ecommers.api.usersAddresses;

import com.miguel.morales.ecommers.api.crud.Generator;
import com.miguel.morales.ecommers.api.usersAddresses.dto.CreateUserAddressDto;
import com.miguel.morales.ecommers.api.usersAddresses.dto.UpdateUserAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users-addresses")
public class UserAddressController {
    @Autowired
    UserAddressService userService;
    @Autowired
    Generator generator;


    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<UserAddressModel> users = userService.getAll();
        return generator.response(users, "done", HttpStatus.OK);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserAddressModel create(@Valid @RequestBody CreateUserAddressDto user) {

        return userService.create(user);
    }

    @PatchMapping(path = "/{id}")
    public UserAddressModel update(@Valid @RequestBody UpdateUserAddressDto user, @PathVariable("id") Long id) {
        return userService.update(user, id);
    }

    @DeleteMapping(path = "/{id}")
    public int delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
