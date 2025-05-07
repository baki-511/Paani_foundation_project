package com.foundation.controller;

import com.foundation.entity.Blog;
import com.foundation.entity.Donation;
import com.foundation.entity.News;
import com.foundation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private NewsService newsService;
    
    @Autowired
    private GalleryService galleryService;
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private DonationService donationService;
    
    @GetMapping({"/", "/home"})
    public String home(Model model) {
//        gallery
        model.addAttribute("gallaries", galleryService.getAllGallery().stream().limit(8).toList());
//        Blog
        List<Blog> blogs = blogService.getAllBlog().stream().limit(3).toList();
        model.addAttribute("blogs", blogs);
//        News
        List<News> newses = newsService.getAllNews().stream().limit(3).toList();
        model.addAttribute("newses", newses);
//        Team
        model.addAttribute("teams", teamService.getAllTeam());
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/about")
    public String aboutUs(Model model) {
        model.addAttribute("teams", teamService.getAllTeam());
        return "/pages/about";
    }
    
    @GetMapping("/contact")
    public String contactUs() {
        return "/pages/contact_us";
    }
    
    @GetMapping("/activity")
    public String activity(Model model) {
        model.addAttribute("activities", activityService.getAllActivity());
        return "/pages/activity";
    }
    
    @GetMapping("/blog/all")
    public String blogs(Model model) {
        model.addAttribute("blogs", blogService.getAllBlog());
        return "/pages/blog";
    }
    
    @GetMapping("/blog/desc/{title}")
    public String blogDesc(@PathVariable String title, Model model) {
        model.addAttribute("blog", blogService.getBlogByTitle(title));
        return "/pages/blog_desc";
    }
    
    @GetMapping("/news/all")
    public String news(Model model) {
        model.addAttribute("newses", newsService.getAllNews());
        return "/pages/news";
    }
    
    @GetMapping("/news/desc/{title}")
    public String newsDesc(@PathVariable String title, Model model) {
        model.addAttribute("news", newsService.getNewsByTitle(title));
        return "/pages/news_desc";
    }
    
    @GetMapping("/gallery/all")
    public String gallery(Model model) {
        model.addAttribute("gallaries", galleryService.getAllGallery());
        return "/pages/gallery";
    }
    
    @GetMapping("/donate")
    public String donation() {
        return "/pages/donation";
    }
    
    
    @PostMapping("/donation/add")
    public String getDonation(@ModelAttribute Donation donation, RedirectAttributes redirectAttributes) {
        Donation addedDonation = donationService.addDonation(donation);
        redirectAttributes.addFlashAttribute("showSuccessModal", true);
        return "redirect:/donate";
    }
    
}
