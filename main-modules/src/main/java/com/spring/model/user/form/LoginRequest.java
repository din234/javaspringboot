package com.spring.model.user.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.user.form.LoginForm;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest implements LoginForm {
    private String username;
    private String password;


    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getPassword() {
        return password;
    }
}
