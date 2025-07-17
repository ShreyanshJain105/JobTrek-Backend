package com.jobtrek.api;

import com.jobtrek.dto.UserDto;
import com.jobtrek.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto>registerUser(@RequestBody @Valid UserDto userDto){
        userDto =userService.registerUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
