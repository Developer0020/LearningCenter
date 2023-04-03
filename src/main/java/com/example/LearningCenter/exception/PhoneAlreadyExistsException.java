package com.example.LearningCenter.exception;

public class PhoneAlreadyExistsException extends RuntimeException{
    public PhoneAlreadyExistsException(String massage) {
        super(massage);
    }
}
