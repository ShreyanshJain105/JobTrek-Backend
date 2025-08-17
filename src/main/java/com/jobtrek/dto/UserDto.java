package com.jobtrek.dto;

import com.jobtrek.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for User information.
 * This class is used to transfer user data between different layers of the application,
 * often for requests (e.g., creating a new user, updating user details) or responses.
 * It includes validation constraints for ensuring data integrity before processing.
 */

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "{user.name.absent}")
    private String name;
    @NotBlank(message = "{user.email.absent}")
    @Email(message = "{user.email.invalid}")
    private String email;
    @NotBlank(message = "{user.password.absent}")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "{user.password.invalid}"
    )
    private String password;
    private AccountType accountType;
    private Long profileId;

    public User toEntity() {
        return new User(this.id,
                this.name,
                this.email,
                this.password,
                this.accountType,
                this.profileId);
    }

}
