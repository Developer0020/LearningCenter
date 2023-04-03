package com.example.LearningCenter.exception;

public class AppBadRequestException extends RuntimeException{
    public AppBadRequestException(String massage) {
        super(massage);
    }
}
