package com.microservices.authservice.services;

import com.microservices.authservice.models.UserCredential;
import com.microservices.authservice.repositories.AuthRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private AuthRepository authRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String saveUser(UserCredential userCredential){
        userCredential.setPassword(
             bCryptPasswordEncoder.encode(userCredential.getPassword())
        );
        authRepository.save(userCredential);
        return "User saved to the system.";
    }
}
