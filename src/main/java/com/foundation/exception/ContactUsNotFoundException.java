package com.foundation.exception;

public class ContactUsNotFoundException extends RuntimeException{
    public ContactUsNotFoundException(String msg) {
        super(msg);
    }
    
    public ContactUsNotFoundException(Long id) {
        super("Contact Us NOT Found with ID : " + id);
    }
}
