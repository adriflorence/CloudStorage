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
        String encodedKey = getEncodedKey();
        String encryptedPassword = getEncryptedPassword(credential.getPassword(), encodedKey);

        return credentialMapper.insert(
            new Credential(
                null,
                credential.getUrl(),
                user.getUserName(),
                encodedKey,
                encryptedPassword,
                user.getUserId()
            )
        );
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }

    public int updateCredential(User user, Credential credential) {
        credential.setPassword(getEncryptedPassword(credential.getPassword(), credential.getKey()));
        return credentialMapper.update(credential);
    }

    private String getEncodedKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey =  Base64.getEncoder().encodeToString(key);
        return encodedKey;
    }

    private String getEncryptedPassword(String password, String encodedKey){
        return encryptionService.encryptValue(password, encodedKey);
    }
}
