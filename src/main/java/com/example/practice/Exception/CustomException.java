package com.example.practice.Exception;

public class CustomException extends Exception{
    private int status_code;
    public CustomException(String message, int status_code) {
        super(message);
        this.status_code = status_code;
    }

    public int getStatus_code() {
        return status_code;
    }
}
