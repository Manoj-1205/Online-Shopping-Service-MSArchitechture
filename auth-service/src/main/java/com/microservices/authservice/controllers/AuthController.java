package com.microservices.authservice.controllers;

import com.microservices.authservice.dtos.UserRequestDto;
import com.microservices.authservice.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto userRequestDto){
        return authService.saveUser(
                userRequestDto.mapToUserCredential()
        );
    }
}
