package com.user.model.form;

public interface UserRegForm extends LoginForm {
    void setEmail(String email);
    String getEmail();

    void setFullName(String fullName);
    String getFullName();
}
