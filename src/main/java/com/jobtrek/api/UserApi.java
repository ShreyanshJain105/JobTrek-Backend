package com.jobtrek.api;

import com.jobtrek.dto.LoginDTO;
import com.jobtrek.dto.ResponseDTO;
import com.jobtrek.dto.UserDto;
import com.jobtrek.exception.JobPortalException;
import com.jobtrek.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
    public ResponseEntity<UserDto>registerUser(@RequestBody @Valid UserDto userDto) throws JobPortalException {
        userDto =userService.registerUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto>loginUser(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.loginUser(loginDTO), HttpStatus.OK);
    }
    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDTO>sendOtp(@PathVariable @Email(message = "{user.email.invalid}") String email) throws Exception {
       userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDTO("OTP sent Successfully."),HttpStatus.OK);
    }

    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDTO> verifyOtp(
            @Email(message = "{user.email.invalid}")
            @PathVariable String email,
            @PathVariable @Pattern(regexp ="^[0-9]{6}$", message="{otp.invalid}" ) String otp) throws JobPortalException {
        userService.verifyOtp(email,otp);
        return new ResponseEntity<>(new ResponseDTO("✅ OTP has been verified successfully!"),HttpStatus.OK);
    }
}
