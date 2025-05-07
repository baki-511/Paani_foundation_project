package com.foundation.service;

import com.foundation.entity.Blog;
import com.foundation.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    Blog addBlog(Blog blog, MultipartFile file);
    
    List<Blog> getAllBlog();
    
    Blog getBlogById(Long blogId);
    
    Blog getBlogByTitle(String title);
    
    ApiResponse deleteBlogById(Long blogId);
    
    Blog updateBlog(Blog blog, MultipartFile file);
}
