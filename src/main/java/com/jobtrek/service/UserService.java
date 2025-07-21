package com.jobtrek.service;

import com.jobtrek.dto.LoginDTO;
import com.jobtrek.dto.UserDto;
import com.jobtrek.exception.JobPortalException;

public interface UserService {
    public UserDto registerUser(UserDto userDto) throws JobPortalException;

    public UserDto loginUser(LoginDTO loginDTO) throws JobPortalException;
}
