package com.foundation.controller;

import com.foundation.dto.DonationDto;
import com.foundation.entity.Donation;
import com.foundation.payload.ApiResponse;
import com.foundation.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminDonationController {
    @Autowired
    private DonationService donationService;
    
    @PostMapping("/donation/add")
    public String getDonation(@ModelAttribute Donation donation, RedirectAttributes redirectAttributes) {
        Donation addedDonation = donationService.addDonation(donation);
        redirectAttributes.addFlashAttribute("showSuccessModal", true);
        return "redirect:/donate";
    }
    
    @GetMapping("/donation/all")
    public String allDonations(Model model) {
        return allDonation(model, 0, 10);
    }
    
    @GetMapping("/donation/all/{page}")
    public String allDonation(Model model, @PathVariable int page, int size) {
        List<DonationDto> allDonationDto = donationService.getAllDonationDto();
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allDonationDto.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<DonationDto> subList = allDonationDto.subList(start, end);
        Page<DonationDto> donationPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("donations", donationPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", donationPage.getTotalPages());
        model.addAttribute("totalItems", donationPage.getTotalElements());
        model.addAttribute("size", size);
        
        
        return "/admin/pages/donation/all-donation";
    }
    
    @GetMapping("/donation/delete/{donationId}")
    public String deleteDonation(@PathVariable Long donationId, RedirectAttributes redirectAttributes) {
        ApiResponse apiResponse = donationService.deleteDonationById(donationId);
        if (apiResponse != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/donation/all";
    }
}
