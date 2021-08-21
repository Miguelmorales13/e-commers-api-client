package com.miguel.morales.ecommers.api.usersAddresses;

import com.miguel.morales.ecommers.api.usersAddresses.dto.CreateUserAddressDto;
import com.miguel.morales.ecommers.api.usersAddresses.dto.UpdateUserAddressDto;
import com.miguel.morales.ecommers.interceptors.anotations.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users-addresses")
public class UserAddressController {
    @Autowired
    UserAddressService userService;


    @GetMapping()
    @ResponseSuccess(message = "get success")
    public List<UserAddressModel> getAll() {
        List<UserAddressModel> users = userService.getAll();
        return users;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseSuccess(message = "creation success")
    public UserAddressModel create(@Valid @RequestBody CreateUserAddressDto user) {

        return userService.create(user);
    }

    @PatchMapping(path = "/{id}")
    @ResponseSuccess(message = "updated success")
    public UserAddressModel update(@Valid @RequestBody UpdateUserAddressDto user, @PathVariable("id") Long id) {
        return userService.update(user, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseSuccess(message = "deleted success")
    public int delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
