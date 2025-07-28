package com.jobtrek.service;

import com.jobtrek.dto.LoginDTO;
import com.jobtrek.dto.ResponseDTO;
import com.jobtrek.dto.UserDto;
import com.jobtrek.exception.JobPortalException;

public interface UserService {
    public UserDto registerUser(UserDto userDto) throws JobPortalException;

    public UserDto loginUser(LoginDTO loginDTO) throws JobPortalException;

    public boolean sendOtp(String email)throws Exception;

   public boolean verifyOtp(String email,String otp) throws JobPortalException;

   public ResponseDTO changePassword( LoginDTO loginDTO) throws JobPortalException;
}
