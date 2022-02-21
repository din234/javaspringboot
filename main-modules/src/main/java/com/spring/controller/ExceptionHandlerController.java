package com.spring.controller;

import com.spring.config.CustomException.InvalidPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {
    Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,Object> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,Object> errors = new HashMap<>();
//        errors.put("Message: ",ex.getMessage());
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(
                    ((FieldError) error).getField(),
                    error.getDefaultMessage()
            );
        });
        return errors;
    }

    @ExceptionHandler(MailException.class)
    public Map<String,Object> emailExceptionHandler(MailException ex){
        Map<String,Object> errors = new HashMap<>();
        errors.put("error_message: ", ex.getMessage());
        return errors;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Map<String,Object> usernameNotFoundExceptionHandler(UsernameNotFoundException ex){
        Map<String,Object> errors = new HashMap<>();
        errors.put("error_message: ", ex.getMessage());
        return errors;
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String,Object> dataIntergrityViolationHandler(){
        Map<String,Object> errors = new HashMap<>();
        errors.put("error: ", "triggered");
        return errors;
    }


















//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(IncorrectFileExtensionException.class)
//    public Map<String,Object> fileExtensionExceptionHandler(IncorrectFileExtensionException ex){
//        return null;
//    }


//    // Password validation bằng phương pháp thủ công
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(InvalidPasswordException.class)
//    public Map<String,Object> passwordExceptionHandler(InvalidPasswordException ex){
//        Map<String,Object> errors = new HashMap<>();
//        errors.put("error_message: ", ex.getMessage());
//        return errors;
//    }
}
