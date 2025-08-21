package com.jobtrek.jwt;

import com.jobtrek.dto.UserDto;
import com.jobtrek.exception.JobPortalException;
import com.jobtrek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserDto dto = userService.getUserByEmail(email);
            return new CustomUserDetails(
                    dto.getId(),
                    email,
                    dto.getPassword(),
                    dto.getAccountType(),
                    new ArrayList<>()
            );
        } catch (JobPortalException e) {
            throw new UsernameNotFoundException("User not found", e);
        }
    }
}
