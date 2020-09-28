package com.chong.usermanagement.security;

import com.chong.usermanagement.domain.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
public class RegisterTemplate {
    private String userId;
    private String password;
    private String email;
    private String address;
    private String team;
    private LocalDateTime workStartDate;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(userId, passwordEncoder.encode(password), email, address, team, workStartDate);
    }
}
