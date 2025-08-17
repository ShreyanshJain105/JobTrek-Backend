package com.jobtrek.service;

import com.jobtrek.dto.ProfileDTO;
import com.jobtrek.exception.JobPortalException;

import java.util.List;

public interface ProfileService {
    public Long createProfile(String email) throws JobPortalException;

    public ProfileDTO getprofile(Long id) throws JobPortalException;

    public ProfileDTO updateprofile(ProfileDTO profileDTO) throws JobPortalException;

    public List<ProfileDTO> getAllprofiles();


}
