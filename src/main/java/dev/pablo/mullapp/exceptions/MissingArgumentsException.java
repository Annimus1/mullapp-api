package dev.pablo.mullapp.exceptions;

public class MissingArgumentsException extends RuntimeException {
    public MissingArgumentsException (String message){
        super(message);
    }
}
