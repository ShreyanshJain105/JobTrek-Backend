package com.jobtrek.api;

import com.jobtrek.jwt.AuthenticationRequest;
import com.jobtrek.jwt.AuthenticationResponse;
import com.jobtrek.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthApi {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // ✅ Load user details
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        // ✅ Pass UserDetails directly to JwtHelper
        final String jwt = jwtHelper.generateToken(userDetails);

        // ✅ Return token in response
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
