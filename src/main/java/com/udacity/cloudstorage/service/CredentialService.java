package com.udacity.cloudstorage.service;

import com.udacity.cloudstorage.mapper.CredentialMapper;
import com.udacity.cloudstorage.model.Credential;
import com.udacity.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredentialsByUserId(Integer userId) {
        return credentialMapper.getCredentialsByUserId(userId);
    }


    public int addCredentials(User user, Credential credential) {
        return credentialMapper.insert(
                new Credential(
                        null,
                        credential.getUrl(),
                        user.getUserName(),
                        credential.getKey(),
                        credential.getPassword(),
                        user.getUserId()
                )
        );
    }
}
