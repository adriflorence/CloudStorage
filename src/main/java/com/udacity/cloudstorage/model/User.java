package com.udacity.cloudstorage.model;

public class User {
    private int userId;
    private String userName;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;

    public User(int userId, String userName, String salt, String password, String firstName, String lastName) {
        this.userId = userId;
        this.userName = userName;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}