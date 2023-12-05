package com.example.swiggato.exception;

public class MenuItemNotFoundException extends RuntimeException{
    public MenuItemNotFoundException(String message){
        super(message);
    }
}
