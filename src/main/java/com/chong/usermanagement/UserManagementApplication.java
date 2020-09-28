package com.chong.usermanagement;

import com.chong.usermanagement.domain.User;
import com.chong.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		User user = new User("kec1226", passwordEncoder.encode("1234"), "dmschd92@naver.com", "suwon", "dev", LocalDateTime.now());
		User user2 = new User("kec1234", passwordEncoder.encode("1234"), "dmschd92@naver.com", "suwon", "dev", LocalDateTime.now());
		User user3 = new User("appleg", passwordEncoder.encode("1234"), "dmschd92@naver.com", "suwon", "dev", LocalDateTime.now());
		userRepository.saveAll(Arrays.asList(user, user2, user3));
	}
}
