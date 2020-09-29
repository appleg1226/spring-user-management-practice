package com.chong.usermanagement.controller;

import com.chong.usermanagement.domain.Client;
import com.chong.usermanagement.repository.ClientRepository;
import com.chong.usermanagement.security.RegisterTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getClientInfo(@PathVariable("id")String id){
        return new ResponseEntity<>(clientRepository.findById(id).get().toString(), HttpStatus.OK);
    }

    @GetMapping("/user/all")
    public ResponseEntity<Iterable<Client>> getClientsInfo(){
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Client> register(@RequestBody RegisterTemplate registerTemplate){
        Client result = registerTemplate.toUser(passwordEncoder);
        clientRepository.save(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/user/modify")
    public ResponseEntity<Client> modify(@RequestBody RegisterTemplate registerTemplate) {
        Client result = registerTemplate.toUser(passwordEncoder);
        clientRepository.save(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
