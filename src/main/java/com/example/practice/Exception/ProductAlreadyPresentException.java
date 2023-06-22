package com.example.practice.Exception;

public class ProductAlreadyPresentException extends Exception{
    public ProductAlreadyPresentException(String message) {
        super(message);
    }
}
