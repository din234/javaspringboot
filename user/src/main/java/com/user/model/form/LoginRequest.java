package com.user.model.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest implements LoginForm {

    @NotEmpty(message = "Missing Username")
    @Length(min = 4, message = "Username must be more than 3 characters!")
    private String username;


    @NotEmpty(message = "Missing Password")
    @Length(min = 6, message = "Password must be more than 6 characters!")
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
