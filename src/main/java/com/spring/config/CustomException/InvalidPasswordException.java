package com.spring.config.CustomException;

import java.io.IOException;

public class InvalidPasswordException extends IOException {
    public InvalidPasswordException(int passwordLength){
        super(passwordLength > 0 ?
                "Password must be at least 6 characters":
                "Missing Password");
    }
}
