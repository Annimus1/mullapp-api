package dev.pablo.mullapp.exceptions;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String message){
        super(message);
    }
}
