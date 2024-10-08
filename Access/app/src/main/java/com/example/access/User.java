package com.example.access;

public class User {
    private String email;
    private String username;
    private String password;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getter methods
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

