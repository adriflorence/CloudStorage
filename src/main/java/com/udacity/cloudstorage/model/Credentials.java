package com.udacity.cloudstorage.model;

public class Credentials {
    private int credentialId;
    private String url;
    private String userName;
    private String key;
    private String password;

    public Credentials(int credentialId, String url, String userName, String key, String password) {
        this.credentialId = credentialId;
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
    }
}
