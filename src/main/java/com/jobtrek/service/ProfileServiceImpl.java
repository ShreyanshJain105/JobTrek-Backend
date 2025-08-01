package com.jobtrek.service;


import com.jobtrek.dto.ProfileDTO;
import com.jobtrek.entity.Profile;
import com.jobtrek.exception.JobPortalException;
import com.jobtrek.repository.ProfileRepository;
import com.jobtrek.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Long createProfile(String email) throws JobPortalException {
        Profile profile = new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();
    }

    @Override
    public ProfileDTO getprofile(Long id) throws JobPortalException {
        return profileRepository.findById(id).orElseThrow(()->new
                JobPortalException("PROFILE_NOT_FOUND")).toDTO();
    }

    @Override
    public ProfileDTO updateprofile(ProfileDTO profileDTO) throws JobPortalException {
      profileRepository.findById(profileDTO.getId()).orElseThrow(()->new
                JobPortalException("PROFILE_NOT_FOUND"));
      profileRepository.save(profileDTO.toEntity());
      return profileDTO;
    }
}
