package com.spring.config.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

// Tạo customs exception cho file excel
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IncorrectFileExtensionException extends IOException {
    public IncorrectFileExtensionException(String errorMessage){
        super(errorMessage);
    }
}
