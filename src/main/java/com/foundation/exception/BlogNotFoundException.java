package com.foundation.exception;

public class BlogNotFoundException extends RuntimeException{
    public BlogNotFoundException(Long blogId) {
        super("Blog NOT Found With ID : " + blogId);
    }
    
    public BlogNotFoundException(String msg) {
        super(msg);
    }
}
