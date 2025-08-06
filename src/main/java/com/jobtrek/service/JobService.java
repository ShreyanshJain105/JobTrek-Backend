package com.jobtrek.service;

import com.jobtrek.dto.ApplicantDTO;
import com.jobtrek.dto.JobDTO;
import com.jobtrek.exception.JobPortalException;
import jakarta.validation.Valid;

import java.util.List;

public interface JobService {

   public  JobDTO postJob( JobDTO jobDTO) throws JobPortalException;

   public List<JobDTO> getAllJobs();

   public   JobDTO getJob(Long id) throws  JobPortalException;

   public void applyJob(Long id, ApplicantDTO applicantDTO) throws  JobPortalException;
}
