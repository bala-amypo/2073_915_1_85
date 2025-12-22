package com.example.demo.service;

import com.example.demo.model.User; // Change from .entity.User to .model.User
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByUsername(String username);
}