package com.chong.usermanagement.domain;

import com.chong.usermanagement.domain.Client;
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

    public Client toUser(PasswordEncoder passwordEncoder){
        return new Client(userId, passwordEncoder.encode(password), email, address, team, workStartDate);
    }
}
