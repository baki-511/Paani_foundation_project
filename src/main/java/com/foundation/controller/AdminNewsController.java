package com.foundation.controller;

import com.foundation.entity.News;
import com.foundation.payload.ApiResponse;
import com.foundation.service.NewsService;
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
public class AdminNewsController {
    
    @Autowired
    private NewsService newsService;
    
    
    @GetMapping("/news/add")
    public String addNews(Model model) {
        model.addAttribute("service", new News());
        return "/admin/pages/news/add-news";
    }
    
    @PostMapping("/news/add")
    public String addNews(@ModelAttribute News news,
                              @RequestParam("imageFile") MultipartFile imageFile,
                          RedirectAttributes redirectAttributes) {
        News savedNews = newsService.addNews(news, imageFile);
        if (savedNews != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/news/all";
    }
    
    @GetMapping("/news/all")
    public String allNews(Model model) {
        return allNews(model, 0, 5);
    }
    
    @GetMapping("/news/all/{page}")
    public String allNews(Model model, @PathVariable int page, int size) {
      
        List<News> allNews = newsService.getAllNews();
        model.addAttribute("news", allNews);
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allNews.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<News> subList = allNews.subList(start, end);
        Page<News> newsPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("news", newsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsPage.getTotalPages());
        model.addAttribute("totalItems", newsPage.getTotalElements());
        model.addAttribute("size", size);
        
        return "/admin/pages/news/all-news";
    }
    
    @GetMapping("/news/edit/{newsId}")
    public String editNews(@PathVariable Long newsId, Model model) {
        News news = newsService.getNewsById(newsId);
        model.addAttribute("news", news);
        return "/admin/pages/news/edit-news";
    }
    
    @PostMapping("/news/edit")
    public String editNews(@ModelAttribute News news,
                              @RequestParam("imageFile") MultipartFile imageFile,
                           RedirectAttributes redirectAttributes ) {
        News updatedNews = newsService.updateNews(news, imageFile);
        if (updatedNews != null) {
            redirectAttributes.addFlashAttribute("message", "Updated Successfully!");
        }
        return "redirect:/admin/news/all";
    }
    
    @GetMapping("/news/delete/{newsId}")
    public String deleteNews(@PathVariable Long newsId, RedirectAttributes redirectAttributes) {
        ApiResponse response = newsService.deleteNewsById(newsId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/news/all";
    }
}
