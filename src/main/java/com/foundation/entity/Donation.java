package com.foundation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationId;
    // Basic donor information
    private String donorName;
    private String email;
    private String mobileNumber;
    private String panNumber; // For tax exemption
    
    // Donation details
    private double amount;
    private LocalDateTime donationDate;
    private boolean needsTaxReceipt;
    private boolean wantsUpdates;
    
    private String payment;
    
    // Payment information (polymorphic approach)
    
    // Tracking information
//    private String transactionId;
    
    
    public Donation() {
    }
    
    public Donation(Long donationId, String donorName, String email, String mobileNumber, String panNumber,
                    double amount, LocalDateTime donationDate, boolean needsTaxReceipt, boolean wantsUpdates) {
        this.donationId = donationId;
        this.donorName = donorName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.panNumber = panNumber;
        this.amount = amount;
        this.donationDate = donationDate;
        this.needsTaxReceipt = needsTaxReceipt;
        this.wantsUpdates = wantsUpdates;
    }
    
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
    
    public LocalDateTime getDonationDate() {
        return donationDate;
    }
    
    public void setDonationDate(LocalDateTime donationDate) {
        this.donationDate = donationDate;
    }
    
    public boolean isNeedsTaxReceipt() {
        return needsTaxReceipt;
    }
    
    public void setNeedsTaxReceipt(boolean needsTaxReceipt) {
        this.needsTaxReceipt = needsTaxReceipt;
    }
    
    public boolean isWantsUpdates() {
        return wantsUpdates;
    }
    
    public void setWantsUpdates(boolean wantsUpdates) {
        this.wantsUpdates = wantsUpdates;
    }
    
    public String getPayment() {
        return payment;
    }
    
    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    @Override
    public String toString() {
        return "Donation{" +
                "donationId=" + donationId +
                ", donorName='" + donorName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", amount=" + amount +
                ", donationDate=" + donationDate +
                ", needsTaxReceipt=" + needsTaxReceipt +
                ", wantsUpdates=" + wantsUpdates +
                ", payment='" + payment + '\'' +
                '}';
    }
}
