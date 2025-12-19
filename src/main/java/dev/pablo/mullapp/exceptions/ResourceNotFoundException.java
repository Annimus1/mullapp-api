package dev.pablo.mullapp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
 
    public ResourceNotFoundException(String message){
        super(message);
    }
}
