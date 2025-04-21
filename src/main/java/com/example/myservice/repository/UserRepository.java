package com.example.myservice.repository;

import java.util.Optional;

import com.example.myservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByUsername(String username);
}
