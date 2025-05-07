package com.foundation.dto;

import java.time.LocalDateTime;

public class DonationDto {
    private Long donationId;
    private String donorName;
    private String email;
    private String mobileNumber;
    private String panNumber;
    
    // Donation details
    private double amount;
    private String donationDate;
    
    private String payment;
    
    public Long getDonationId() {
        return donationId;
    }
    
    public void setDonationId(Long donationId) {
        this.donationId = donationId;
    }
    
    public String getDonorName() {
        return donorName;
    }
    
    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getPanNumber() {
        return panNumber;
    }
    
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getDonationDate() {
        return donationDate;
    }
    
    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }
    
    public String getPayment() {
        return payment;
    }
    
    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    @Override
    public String toString() {
        return "DonationDto{" +
                "donorName='" + donorName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", amount=" + amount +
                ", donationDate='" + donationDate + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}
