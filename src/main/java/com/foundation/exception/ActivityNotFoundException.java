package com.foundation.exception;

public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(Long activityId) {
        super("Activity NOT Found With ID : " + activityId);
    }
    
    
    public ActivityNotFoundException(String msg) {
        super(msg);
    }
}
