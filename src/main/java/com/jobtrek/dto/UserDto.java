package com.jobtrek.dto;

import com.jobtrek.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
        private String id;
        private String name;
        private String email;
        private String password;
        private AccountType accountType;

    public User toEntity(){
        return new User(
                this.id,
                this.name,
                this.email,
                this.password,
                this.accountType);
    }

}
