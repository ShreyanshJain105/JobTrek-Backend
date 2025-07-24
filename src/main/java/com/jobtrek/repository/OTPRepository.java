package com.jobtrek.repository;

import com.jobtrek.entity.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OTPRepository extends MongoRepository<OTP,String> {
}
