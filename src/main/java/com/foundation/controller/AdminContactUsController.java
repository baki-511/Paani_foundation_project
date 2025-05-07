package com.foundation.controller;

import com.foundation.entity.ContactUs;
import com.foundation.payload.ApiResponse;
import com.foundation.service.ContactUsService;
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
public class AdminContactUsController {
    @Autowired
    private ContactUsService contactUsService;
    
    
    @PostMapping("/contact/save")
    public String saveContact(@ModelAttribute ContactUs contactUs, RedirectAttributes redirectAttributes) {
        System.out.println(contactUs);
        ContactUs savedContactUs = contactUsService.addContactUs(contactUs);
        if (savedContactUs != null) {
            redirectAttributes.addFlashAttribute("message", "Message Sent Successfully!");
        }
        return "redirect:/contact";
    }
    
    @GetMapping("/contact/all")
    public String allContactus(Model model) {
        return allContactUs(model, 0, 10);
    }
    
    @GetMapping("/contact/all/{page}")
    public String allContactUs(Model model, @PathVariable int page, int size) {
        List<ContactUs> allContactUs = contactUsService.getAllContactUs();
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allContactUs.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<ContactUs> subList = allContactUs.subList(start, end);
        Page<ContactUs> contactUsPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("contacts", contactUsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contactUsPage.getTotalPages());
        model.addAttribute("totalItems", contactUsPage.getTotalElements());
        model.addAttribute("size", size);
        
        
        return "/admin/pages/contactUs/all-contactUs";
    }
    
    @GetMapping("/contact/delete/{contactId}")
    public String deleteContactUs(@PathVariable Long contactId, RedirectAttributes redirectAttributes) {
        ApiResponse apiResponse = contactUsService.deleteContactUs(contactId);
        if (apiResponse != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/contact/all";
    }
}
