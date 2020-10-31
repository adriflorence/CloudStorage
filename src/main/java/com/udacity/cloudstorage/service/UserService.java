package com.udacity.cloudstorage.service;

import com.udacity.cloudstorage.mapper.UserMapper;
import com.udacity.cloudstorage.model.User;

import java.security.SecureRandom;
import java.util.Base64;

public class UserService {

    private final HashService hashService;
    private final UserMapper userMapper;

    public UserService(HashService hashService, UserMapper userMapper) {
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        // save secured user credentials into db
        return userMapper.insert(
            new User(
                user.getUserName(),
                encodedSalt,
                hashedPassword,
                user.getFirstName(),
                user.getLastName())
        );
    }
}
