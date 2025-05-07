package com.foundation.controller;

import com.foundation.entity.Gallery;
import com.foundation.payload.ApiResponse;
import com.foundation.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminGalleryController {
    
    @Autowired
    private GalleryService galleryService;
    
    
    @GetMapping("/gallery/add")
    public String addGallery(Model model) {
        model.addAttribute("service", new Gallery());
        return "/admin/pages/gallery/add-gallery";
    }
    
    @PostMapping("/gallery/add")
    public String addGallery(@ModelAttribute Gallery gallery,
                              @RequestParam("imageFile") MultipartFile imageFile,
                          RedirectAttributes redirectAttributes) {
        Gallery savedGallery = galleryService.addGallery(imageFile);
        if (savedGallery != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/gallery/all";
    }
    
    @GetMapping("/gallery/all")
    public String allGallery(Model model) {
        return allGallery(model, 0, 5);
    }
    
    @GetMapping("/gallery/all/{page}")
    public String allGallery(Model model, @PathVariable int page, int size) {
      
        List<Gallery> allGallery = galleryService.getAllGallery();
        model.addAttribute("gallery", allGallery);
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allGallery.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<Gallery> subList = allGallery.subList(start, end);
        Page<Gallery> galleryPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("gallerys", galleryPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", galleryPage.getTotalPages());
        model.addAttribute("totalItems", galleryPage.getTotalElements());
        model.addAttribute("size", size);
        
        return "/admin/pages/gallery/all-gallery";
    }
    
    @GetMapping("/gallery/delete/{galleryId}")
    public String deleteGallery(@PathVariable Long galleryId, RedirectAttributes redirectAttributes) {
        ApiResponse response = galleryService.deleteGalleryById(galleryId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/gallery/all";
    }
}
