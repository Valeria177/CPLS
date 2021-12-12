package com.sas.alex.dto.auth.request;

import javax.validation.constraints.*;

public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private boolean sexF;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getSexF() {
        return sexF;
    }

    public void setSexF(boolean sexF) {
        this.sexF = sexF;
    }
}
