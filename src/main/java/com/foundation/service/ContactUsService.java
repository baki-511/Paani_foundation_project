package com.foundation.service;

import com.foundation.entity.ContactUs;
import com.foundation.payload.ApiResponse;

import java.util.List;

public interface ContactUsService {
    public ContactUs addContactUs(ContactUs contactUs);
    
    public List<ContactUs> getAllContactUs();
    
    public ContactUs getContactUsById(Long contactUsId);
    
    public ApiResponse deleteContactUs(Long contactUsId);
    
    public ContactUs updateContactUs(ContactUs contactUs);
    
}
