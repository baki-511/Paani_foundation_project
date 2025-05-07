package com.foundation.controller;

import com.foundation.entity.Activity;
import com.foundation.payload.ApiResponse;
import com.foundation.service.ActivityService;
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
public class AdminActivityController {
    @Autowired
    private ActivityService activityService;
    
    @GetMapping("/activity/add")
    public String addActivity(Model model) {
        model.addAttribute("activity", new Activity());
        return "/admin/pages/activity/add-activity";
    }
    
    @PostMapping("/activity/add")
    public String addActivity(@ModelAttribute Activity activity,
                            @RequestParam("imageFile") MultipartFile imageFile,
                            RedirectAttributes redirectAttributes) {
        Activity savedActivity = activityService.addActivity(activity, imageFile);
        if (savedActivity != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/activity/all";
    }
    
    @GetMapping("/activity/all")
    public String allActivity(Model model) {
        return allActivity(model, 0, 5);
    }
    
    @GetMapping("/activity/all/{page}")
    public String allActivity(Model model, @PathVariable int page, int size) {
        List<Activity> allActivity = activityService.getAllActivity();
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allActivity.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<Activity> subList = allActivity.subList(start, end);
        Page<Activity> activityPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("activity", activityPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", activityPage.getTotalPages());
        model.addAttribute("totalItems", activityPage.getTotalElements());
        model.addAttribute("size", size);
        
        return "/admin/pages/activity/all-activity";
    }
    
    @GetMapping("/activity/edit/{activityId}")
    public String editActivity(@PathVariable Long activityId, Model model) {
        Activity activity = activityService.getActivityById(activityId);
        model.addAttribute("activity", activity);
        return "/admin/pages/activity/edit-activity";
    }
    
    @PostMapping("/activity/edit")
    public String editActivity(@ModelAttribute Activity activity,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {
        Activity updateActivity = activityService.updateActivity(activity, imageFile);
        if (updateActivity != null) {
            redirectAttributes.addFlashAttribute("message", "Updated Successfully!");
        }
        return "redirect:/admin/activity/all";
    }
    
    @GetMapping("/activity/delete/{activityId}")
    public String deleteActivity(@PathVariable Long activityId, RedirectAttributes redirectAttributes) {
        ApiResponse response = activityService.deleteActivityById(activityId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/activity/all";
    }
    
}
