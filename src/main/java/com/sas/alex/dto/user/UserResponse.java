package com.sas.alex.dto.user;

public class UserResponse {
    private String username;
    private boolean sexF;
    private String email;

    public UserResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSexF() {
        return sexF;
    }

    public void setSexF(boolean sexF) {
        this.sexF = sexF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
