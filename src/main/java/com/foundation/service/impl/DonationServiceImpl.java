package com.foundation.service.impl;

import com.foundation.dto.DonationDto;
import com.foundation.entity.Donation;
import com.foundation.exception.DonationNotFoundException;
import com.foundation.payload.ApiResponse;
import com.foundation.repository.DonationRepository;
import com.foundation.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationRepository donationRepository;
    
    @Override
    public Donation addDonation(Donation donation) {
        donation.setDonationDate(LocalDateTime.now());
        return donationRepository.save(donation);
    }
    
    @Override
    public List<Donation> getAllDonation() {
        return donationRepository.findAll();
    }
    
    @Override
    public List<DonationDto> getAllDonationDto() {
        List<DonationDto> allDonation = new ArrayList<>();
        
        for (Donation donation : getAllDonation()) {
            DonationDto dto = new DonationDto();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
            String format = donation.getDonationDate().format(formatter);
            dto.setDonationDate(format);
            
            dto.setDonationId(donation.getDonationId());
            dto.setAmount(donation.getAmount());
            dto.setDonorName(donation.getDonorName());
            dto.setEmail(donation.getEmail());
            dto.setMobileNumber(donation.getMobileNumber());
            dto.setPayment(donation.getPayment());
            dto.setPanNumber(donation.getPanNumber());
            
            allDonation.add(dto);
        }
        return allDonation;
    }
    
    @Override
    public Donation getDonationById(Long donationId) {
        return donationRepository.findById(donationId)
                .orElseThrow(() -> new DonationNotFoundException(donationId));
    }
    
    @Override
    public Double getTotalDonation() {
        return getAllDonation()
                .stream()
                .mapToDouble(Donation::getAmount)
                .sum();
    }
    
    @Override
    public Double getTotalDonationThisMonth() {
        return getAllDonation()
                .stream()
                .filter(d -> YearMonth.from(d.getDonationDate()).equals(YearMonth.now()))
                .mapToDouble(Donation::getAmount)
                .sum();
    }
    
    
    @Override
    public ApiResponse deleteDonationById(Long donationId) {
        Donation donationById = getDonationById(donationId);
        donationRepository.delete(donationById);
        return new ApiResponse("Donation Deleted Successfully!", true);
    }
    
    @Override
    public Donation updateDonation(Donation donation) {
        Donation donationById = getDonationById(donation.getDonationId());
        return donationRepository.save(donation);
    }
    
}
