package com.foundation.service.impl;

import com.foundation.entity.Blog;
import com.foundation.exception.BlogNotFoundException;
import com.foundation.payload.ApiResponse;
import com.foundation.repository.BlogRepository;
import com.foundation.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    
    @Override
    public Blog addBlog(Blog blog, MultipartFile file) {
        String image = getImageToString(file);
        blog.setImage(image);
        return blogRepository.save(blog);
    }
    
    @Override
    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }
    
    @Override
    public Blog getBlogById(Long blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException(blogId));
    }
    
    @Override
    public Blog getBlogByTitle(String title) {
        return blogRepository.findBlogByTitle(title)
                .orElseThrow(()->new BlogNotFoundException("Blog NOT Found!"));
    }
    
    
    @Override
    public ApiResponse deleteBlogById(Long blogId) {
        Blog blogById = getBlogById(blogId);
        blogRepository.delete(blogById);
        return new ApiResponse("Blog Deleted Successfully!", true);
    }
    
    @Override
    public Blog updateBlog(Blog blog, MultipartFile file) {
        Blog blogById = getBlogById(blog.getBlogId());
        
        if (file.isEmpty()) {
            blog.setImage(blogById.getImage());
        } else {
            String image = getImageToString(file);
            blog.setImage(image);
        }
        return blogRepository.save(blog);
    }
    
    
    private String getImageToString(MultipartFile file) {
        String image = "";
        try {
            image = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
