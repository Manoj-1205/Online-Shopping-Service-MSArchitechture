package com.microservices.authservice.dtos;

import com.microservices.authservice.models.UserCredential;
import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String password;

    public UserCredential mapToUserCredential(){
        return UserCredential.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
