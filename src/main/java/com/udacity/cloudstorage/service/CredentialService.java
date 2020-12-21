package com.udacity.cloudstorage.service;

import com.udacity.cloudstorage.mapper.CredentialMapper;
import com.udacity.cloudstorage.model.Credential;
import com.udacity.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentialsByUserId(Integer userId) {
        return credentialMapper.getCredentialsByUserId(userId);
    }


    public int addCredential(User user, Credential credential) {
        String encryptedPassword = getEncryptedPassword(credential);

        return credentialMapper.insert(
            new Credential(
                null,
                credential.getUrl(),
                user.getUserName(),
                credential.getKey(),
                encryptedPassword,
                user.getUserId()
            )
        );
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }

    public int updateCredential(User user, Credential credential) {
        return credentialMapper.update(credential);
    }

    private String getEncryptedPassword(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey =  Base64.getEncoder().encodeToString(key);
        return encryptionService.encryptValue(credential.getPassword(), encodedKey);
    }
}
