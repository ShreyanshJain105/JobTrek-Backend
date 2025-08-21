package com.jobtrek.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String jwt;

}
