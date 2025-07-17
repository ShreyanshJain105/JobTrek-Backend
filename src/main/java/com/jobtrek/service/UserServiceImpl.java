package com.jobtrek.service;

import com.jobtrek.dto.UserDto;
import com.jobtrek.entity.User;
import com.jobtrek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user =userDto.toEntity();
        user=userRepository.save(user);
        return user.toDTO();
    }
}
