package com.microservices.authservice.repositories;

import com.microservices.authservice.models.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserCredential, Long> {
}
