package com.jobtrek.service;

import com.jobtrek.dto.LoginDTO;
import com.jobtrek.dto.ResponseDTO;
import com.jobtrek.dto.UserDto;
import com.jobtrek.entity.OTP;
import com.jobtrek.entity.User;
import com.jobtrek.exception.JobPortalException;
import com.jobtrek.repository.OTPRepository;
import com.jobtrek.repository.UserRepository;
import com.jobtrek.utility.Data;
import com.jobtrek.utility.Utilities;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public UserDto registerUser(UserDto userDto) throws JobPortalException {
        Optional<User> optional=userRepository.findByEmail(userDto.getEmail());
        if(optional.isPresent())throw new JobPortalException("USER_FOUND");
        userDto.setId(Utilities.getNextSequence("users"));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user =userDto.toEntity();
        user=userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDto loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() ->
                new JobPortalException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new JobPortalException("INVALID_CREDENTIALS");
        }
        return user.toDTO();
    }

    @Override
    public boolean sendOtp(String email) throws Exception {
        User user =userRepository.findByEmail(email).orElseThrow(() ->
                new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mm= mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm,true);
        message.setTo(email);
        message.setSubject("Your OTP Code");
        String genOTP=Utilities.generateOTP();
        OTP otp=new OTP(email,genOTP, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText(Data.getMessageBody(genOTP,user.getName()),true);
        mailSender.send(mm);
        return true;



    }

    @Override
    public boolean verifyOtp(String email,String otp) throws JobPortalException {
        OTP otpEntity =otpRepository.findById(email).orElseThrow(()->new JobPortalException("OTP_Not_Found!"));
        if(!otpEntity.getOtpCode().equals(otp))throw new JobPortalException("OTP_Incorrect");
        return true;
    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        User user =userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        return new ResponseDTO("Password changed Successfully!");
    }

    @Scheduled(fixedRate = 60000)
    public void removeExpiredOTPs(){
        LocalDateTime expiry= LocalDateTime.now().minusMinutes(5);
        List<OTP>expiredOTPs=otpRepository.findByCreationTimeBefore(expiry);
        if(!expiredOTPs.isEmpty()){
            otpRepository.deleteAll(expiredOTPs);
            System.out.println("Removed "+expiredOTPs.size()+" expired OTPs");

        }

    }

}
