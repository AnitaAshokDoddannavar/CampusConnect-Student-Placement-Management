package com.campusconnect.service;

import java.util.Optional;

import com.campusconnect.entity.User;

public interface UserService {

    Optional<User> findByUsername(String username);
}