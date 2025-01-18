package com.example.CronPaserCLI.exceptions;

public class InvalidFormatException extends RuntimeException{
    public InvalidFormatException(String message){
        super(message);
    }
}
