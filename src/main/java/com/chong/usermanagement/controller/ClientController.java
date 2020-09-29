package com.chong.usermanagement.controller;

import com.chong.usermanagement.domain.Client;
import com.chong.usermanagement.domain.Login;
import com.chong.usermanagement.repository.ClientRepository;
import com.chong.usermanagement.security.JwtTokenProvider;
import com.chong.usermanagement.domain.RegisterTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log
public class ClientController {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원 가입
    @PostMapping("/register")
    public ResponseEntity<Client> register(@RequestBody RegisterTemplate registerTemplate){
        Client result = registerTemplate.toUser(passwordEncoder);
        clientRepository.save(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
        Client client = clientRepository.findById(login.getId()).orElseThrow(()->
                new IllegalArgumentException("가입되지 않은 ID"));
        if(!passwordEncoder.matches(login.getPassword(), client.getPassword())){
            throw new IllegalArgumentException("비밀번호가 맞지 않음.");
        }
        log.info(client.getClientId() + " login success");
        return new ResponseEntity<>(jwtTokenProvider.createToken(client.getClientId()), HttpStatus.OK);
    }

    // 유저 정보 조회 및 수정
    //
    @PutMapping("/user/modify")
    public ResponseEntity<Client> modify(@RequestBody RegisterTemplate registerTemplate) {
        Client result = registerTemplate.toUser(passwordEncoder);
        clientRepository.save(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getClientInfo(@PathVariable("id")String id){
        return new ResponseEntity<>(clientRepository.findById(id).get().toString(), HttpStatus.OK);
    }

    @GetMapping("/user/all")
    public ResponseEntity<Iterable<Client>> getClientsInfo(){
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }
}
