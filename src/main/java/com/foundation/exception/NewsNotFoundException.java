package com.foundation.exception;

public class NewsNotFoundException extends RuntimeException{
    public NewsNotFoundException(Long newsId) {
        super("News NOT Found With ID : " + newsId);
    }
    
    public NewsNotFoundException(String msg) {
        super(msg);
    }
}
