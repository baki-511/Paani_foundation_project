package com.foundation.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId) {
        super("User NOT Found With ID : " + userId);
    }
    
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
