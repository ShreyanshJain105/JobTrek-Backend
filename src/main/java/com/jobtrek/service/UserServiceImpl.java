package com.jobtrek.service;

import com.jobtrek.dto.LoginDTO;
import com.jobtrek.dto.UserDto;
import com.jobtrek.entity.User;
import com.jobtrek.exception.JobPortalException;
import com.jobtrek.repository.UserRepository;
import com.jobtrek.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) throws JobPortalException {
        Optional<User> optional=userRepository.findByEmail(userDto.getEmail());
        if(optional.isPresent())throw new JobPortalException("USER_FOUND");
        userDto.setId(Utilities.getNextSequence("users"));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user =userDto.toEntity();
        user=userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDto loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() ->
                new JobPortalException("USER_NOT_FOUND"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new JobPortalException("INVALID_CREDENTIALS");
        }
        return user.toDTO();
    }

}
