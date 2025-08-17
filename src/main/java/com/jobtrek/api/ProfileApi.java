package com.jobtrek.api;

import com.jobtrek.dto.ProfileDTO;
import com.jobtrek.exception.JobPortalException;
import com.jobtrek.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/profiles")
public class ProfileApi {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<Long> createProfile(@RequestParam String email, @RequestParam String name) throws JobPortalException {
        return new ResponseEntity<>(profileService.createProfile(email, name), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) throws JobPortalException {
        return new ResponseEntity<>(profileService.getprofile(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) throws JobPortalException {
        return new ResponseEntity<>(profileService.updateprofile(profileDTO), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() throws JobPortalException {
        return new ResponseEntity<>(profileService.getAllprofiles(), HttpStatus.OK);
    }
}
