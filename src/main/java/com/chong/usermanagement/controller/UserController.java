package com.chong.usermanagement.controller;

import com.chong.usermanagement.domain.User;
import com.chong.usermanagement.repository.UserRepository;
import com.chong.usermanagement.security.RegisterTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/info/{id}")
    public ResponseEntity<String> getUserInfo(@PathVariable("id")String id){
        return new ResponseEntity<>(userRepository.findById(id).get().toString(), HttpStatus.OK);
    }

    @GetMapping("/info/all")
    public ResponseEntity<Iterable<User>> getUsersInfo(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterTemplate registerTemplate){
        User result = registerTemplate.toUser(passwordEncoder);
        userRepository.save(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<User> modify(@RequestBody RegisterTemplate registerTemplate) {
        User result = registerTemplate.toUser(passwordEncoder);
        userRepository.save(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
