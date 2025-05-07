package com.foundation.exception;

public class DonationNotFoundException extends RuntimeException {
    public DonationNotFoundException(Long donationId) {
        super("Donation NOT Found With ID: " + donationId);
    }
    
    public DonationNotFoundException(String msg) {
        super(msg);
    }
}
