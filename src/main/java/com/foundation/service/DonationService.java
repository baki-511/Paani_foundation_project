package com.foundation.service;

import com.foundation.dto.DonationDto;
import com.foundation.entity.Donation;
import com.foundation.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DonationService {
    Donation addDonation(Donation donation);
    
    List<Donation> getAllDonation();
    
    List<DonationDto> getAllDonationDto();
    Donation getDonationById(Long donationId);
    
    Double getTotalDonation();
    
    Double getTotalDonationThisMonth();
    ApiResponse deleteDonationById(Long donationId);
    
    Donation updateDonation(Donation donation);
}
