package com.chong.usermanagement;

import com.chong.usermanagement.domain.Client;
import com.chong.usermanagement.repository.ClientRepository;
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
	ClientRepository clientRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		Client client = new Client("kec1226", passwordEncoder.encode("1234"), "dmschd92@naver.com", "suwon", "dev", LocalDateTime.now());
		Client client2 = new Client("kec1234", passwordEncoder.encode("1234"), "dmschd92@naver.com", "suwon", "dev", LocalDateTime.now());
		Client client3 = new Client("appleg", passwordEncoder.encode("1234"), "dmschd92@naver.com", "suwon", "dev", LocalDateTime.now());
		clientRepository.saveAll(Arrays.asList(client, client2, client3));
	}
}
