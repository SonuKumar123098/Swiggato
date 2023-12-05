package com.example.swiggato.exception;

public class RestaurantClosedException extends RuntimeException{
    public RestaurantClosedException(String message){
        super(message);
    }
}
