package com.example.smartdeals.exception;

public class InsufficientQuantityException extends RuntimeException{

    public InsufficientQuantityException(String message){
        super(message);
    }
}
