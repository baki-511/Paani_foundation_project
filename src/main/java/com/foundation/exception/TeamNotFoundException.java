package com.foundation.exception;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(Long teamId) {
        super("Team NOT Found With ID : " + teamId);
    }
    
    public TeamNotFoundException(String msg) {
        super(msg);
    }
}
