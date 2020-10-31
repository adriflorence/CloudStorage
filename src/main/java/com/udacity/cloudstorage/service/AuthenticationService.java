package com.udacity.cloudstorage.service;

import com.udacity.cloudstorage.mapper.UserMapper;
import com.udacity.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

    private final UserMapper userMapper;
    private final HashService hashService;

    @Autowired
    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    // authenticate method is used by Spring Security to check credentials
    // generic interface to be integrated with many authentication schemes
    // returns an authentication token if the user's credentials are correct
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userMapper.getUser(username);
        if(user != null) {
            String userEncodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, userEncodedSalt);
            // if hashed pw matches the one in the DB, user logged in successfully
            if(user.getPassword().equals(hashedPassword)) {
                // 3rd parameter: a list of granted authorities (permissions)
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }

        return null;
    }

    // tells Spring which authentication schemes this class supports
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
