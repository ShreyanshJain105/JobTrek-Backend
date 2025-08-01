/**
 * Service implementation class for user-related operations in the JobTrek application.
 * Handles user registration, authentication, OTP verification, and password management.
 */
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

    // Repository for user data access operations
    @Autowired
    private UserRepository userRepository;

    // Repository for OTP data access operations
    @Autowired
    private OTPRepository otpRepository;

    // Password encoder for secure password hashing
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Java mail sender for sending email notifications
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ProfileService profileService;

    /**
     * Registers a new user in the system.
     * Validates that the email is not already registered, generates a unique ID,
     * encrypts the password, and saves the user to the database.
     *
     * @param userDto User data transfer object containing registration details
     * @return UserDto containing the registered user information
     * @throws JobPortalException if user with email already exists
     */

    @Override
    public UserDto registerUser(UserDto userDto) throws JobPortalException {

        Optional<User> optional=userRepository.findByEmail(userDto.getEmail());            // Check if user with this email already exists
        if(optional.isPresent())throw new JobPortalException("USER_FOUND");
        userDto.setProfileId(profileService.createProfile(userDto.getEmail()));
        userDto.setId(Utilities.getNextSequence("users"));                               // Generate unique sequence ID for the new user
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));                 // Encrypt the password before storing
        User user =userDto.toEntity();                                                     // Convert DTO to entity and save to database
        user=userRepository.save(user);
        return user.toDTO();  // Return the saved user as DTO
    }

    /**
     * Authenticates a user login attempt.
     * Validates email exists and password matches the stored encrypted password.
     *
     * @param loginDTO Login credentials containing email and password
     * @return UserDto containing authenticated user information
     * @throws JobPortalException if user not found or credentials are invalid
     */

    @Override
    public UserDto loginUser(LoginDTO loginDTO) throws JobPortalException {
        // Find user by email, throw exception if not found
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() ->
                new JobPortalException("USER_NOT_FOUND"));
        // Verify the provided password against the stored encrypted password
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new JobPortalException("INVALID_CREDENTIALS");
        }
        return user.toDTO();
    }

    /**
     * Generates and sends OTP (One-Time Password) to user's email for verification.
     * Creates a new OTP record in database and sends formatted email with OTP code.
     *
     * @param email User's email address to send OTP to
     * @return boolean true if OTP was successfully sent
     * @throws Exception if user not found or email sending fails
     */
    @Override
    public boolean sendOtp(String email) throws Exception {
        // Verify user exists with the provided email
        User user =userRepository.findByEmail(email).orElseThrow(() ->
                new JobPortalException("USER_NOT_FOUND"));
        // Create MIME message for HTML email
        MimeMessage mm= mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm,true);
        // Set email recipient and subject
        message.setTo(email);
        message.setSubject("Your OTP Code");
        String genOTP=Utilities.generateOTP(); // Generate random OTP code
        OTP otp=new OTP(email,genOTP, LocalDateTime.now());   // Create and save OTP entity with current timestamp
        otpRepository.save(otp);
        message.setText(Data.getMessageBody(genOTP,user.getName()),true);  // Set email body with personalized message containing OTP
        mailSender.send(mm);  // Send the email
        return true;



    }

    /**
     * Verifies the OTP code provided by user against stored OTP.
     * Validates that OTP exists for the email and matches the provided code.
     *
     * @param email User's email address
     * @param otp OTP code to verify
     * @return boolean true if OTP is valid
     * @throws JobPortalException if OTP not found or incorrect
     */

    @Override
    public boolean verifyOtp(String email,String otp) throws JobPortalException {
        // Retrieve OTP record for the email, throw exception if not found
        OTP otpEntity =otpRepository.findById(email).orElseThrow(()->new JobPortalException("OTP_Not_Found!"));
        // Compare provided OTP with stored OTP code
        if(!otpEntity.getOtpCode().equals(otp))throw new JobPortalException("OTP_Incorrect");
        return true;
    }

    /**
     * Changes user's password after validation.
     * Updates the user's password with encrypted version of the new password.
     *
     * @param loginDTO Contains email and new password
     * @return ResponseDTO with success message
     * @throws JobPortalException if user not found
     */

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        // Find user by email, throw exception if not found
        User user =userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        // Encrypt and set the new password
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        // Save updated user information
        userRepository.save(user);
        return new ResponseDTO("Password changed Successfully!");// Return success response
    }


    /**
     * Scheduled task that runs every minute (60000 ms) to clean up expired OTPs.
     * Removes OTP records that are older than 5 minutes from creation time.
     * This prevents database bloat and ensures OTPs have limited validity period.
     */
    @Scheduled(fixedRate = 60000)
    public void removeExpiredOTPs(){
        // Calculate expiry time (5 minutes ago from current time)
        LocalDateTime expiry= LocalDateTime.now().minusMinutes(5);
        // Find all OTPs created before the expiry time
        List<OTP>expiredOTPs=otpRepository.findByCreationTimeBefore(expiry);
        // Delete expired OTPs if any exist
        if(!expiredOTPs.isEmpty()){
            otpRepository.deleteAll(expiredOTPs);
            System.out.println("Removed "+expiredOTPs.size()+" expired OTPs");

        }

    }

}
