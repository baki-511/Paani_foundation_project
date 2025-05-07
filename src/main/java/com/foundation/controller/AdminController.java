package com.foundation.controller;

import com.foundation.dto.DonationDto;
import com.foundation.entity.ContactUs;
import com.foundation.entity.Donation;
import com.foundation.payload.ApiResponse;
import com.foundation.service.ContactUsService;
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

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ContactUsService contactUsService;
    
    @Autowired
    private DonationService donationService;
    
    @GetMapping("/")
    public String home(Model model) {
        List<DonationDto> lastFiveDonations = donationService.getAllDonationDto()
                .stream()
                .sorted(Comparator.comparing(DonationDto::getDonationId).reversed())
                .limit(5).toList();
        model.addAttribute("donations", lastFiveDonations);
        model.addAttribute("totalDonation", donationService.getTotalDonation());
        model.addAttribute("thisMonthDonation", donationService.getTotalDonationThisMonth());
        return "/admin/index";
    }
    
    @PostMapping("/sample/save")
    public String sampleSave(@ModelAttribute ContactUs contactUs) {
        System.out.println("Message received!");
        return "redirect:/contact";
    }
}
