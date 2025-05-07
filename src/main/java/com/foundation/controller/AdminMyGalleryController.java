package com.foundation.controller;

import com.foundation.entity.MyGallery;
import com.foundation.payload.ApiResponse;
import com.foundation.service.MyGalleryService;
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
public class AdminMyGalleryController {
    
    @Autowired
    private MyGalleryService myGalleryService;
    
    
    @GetMapping("/myGallery/add")
    public String addMyGallery(Model model) {
        model.addAttribute("service", new MyGallery());
        return "/admin/pages/myGallery/add-myGallery";
    }
    
    @PostMapping("/myGallery/add")
    public String addMyGallery(@ModelAttribute MyGallery myGallery,
                              @RequestParam("imageFile") MultipartFile imageFile,
                          RedirectAttributes redirectAttributes) {
        MyGallery savedMyGallery = myGalleryService.addMyGallery(myGallery, imageFile);
        if (savedMyGallery != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/myGallery/all";
    }
    
    @GetMapping("/myGallery/all")
    public String allMyGallery(Model model) {
        return allMyGallery(model, 0, 5);
    }
    
    @GetMapping("/myGallery/all/{page}")
    public String allMyGallery(Model model, @PathVariable int page, int size) {
      
        List<MyGallery> allMyGallery = myGalleryService.getAllMyGallery();
//        model.addAttribute("myGallery", allMyGallery);
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allMyGallery.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<MyGallery> subList = allMyGallery.subList(start, end);
        Page<MyGallery> myGalleryPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("galleries", myGalleryPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", myGalleryPage.getTotalPages());
        model.addAttribute("totalItems", myGalleryPage.getTotalElements());
        model.addAttribute("size", size);
        
        return "/admin/pages/myGallery/all-myGallery";
    }
    
    @GetMapping("/myGallery/edit/{myGalleryId}")
    public String editMyGallery(@PathVariable Long myGalleryId, Model model) {
        MyGallery myGallery = myGalleryService.getMyGalleryById(myGalleryId);
        model.addAttribute("myGallery", myGallery);
        return "/admin/pages/myGallery/edit-myGallery";
    }
    
    @PostMapping("/myGallery/edit")
    public String editMyGallery(@ModelAttribute MyGallery myGallery,
                              @RequestParam("imageFile") MultipartFile imageFile,
                           RedirectAttributes redirectAttributes ) {
        MyGallery updatedMyGallery = myGalleryService.updateMyGallery(myGallery, imageFile);
        if (updatedMyGallery != null) {
            redirectAttributes.addFlashAttribute("message", "Updated Successfully!");
        }
        return "redirect:/admin/myGallery/all";
    }
    
    @GetMapping("/myGallery/delete/{myGalleryId}")
    public String deleteMyGallery(@PathVariable Long myGalleryId, RedirectAttributes redirectAttributes) {
        ApiResponse response = myGalleryService.deleteMyGalleryById(myGalleryId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/myGallery/all";
    }
}
