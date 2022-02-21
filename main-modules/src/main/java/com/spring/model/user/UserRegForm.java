package com.spring.model.user;

public interface UserRegForm extends LoginForm{
    void setEmail(String email);
    String getEmail();

    void setFullName(String fullName);
    String getFullName();
}
