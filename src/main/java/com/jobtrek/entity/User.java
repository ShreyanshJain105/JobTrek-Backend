package com.jobtrek.entity;

import com.jobtrek.dto.AccountType;
import com.jobtrek.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private Long id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private AccountType accountType;

    public UserDto toDTO(){
        return new UserDto(
                this.id,
                this.name,
                this.email,
                this.password,
                this.accountType);

    }
}
