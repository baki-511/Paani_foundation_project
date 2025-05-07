package com.foundation.service.impl;

import com.foundation.entity.News;
import com.foundation.exception.NewsNotFoundException;
import com.foundation.payload.ApiResponse;
import com.foundation.repository.NewsRepository;
import com.foundation.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    
    @Override
    public News addNews(News news, MultipartFile file) {
        String image = getImageToString(file);
        news.setImage(image);
        return newsRepository.save(news);
    }
    
    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
    
    @Override
    public News getNewsById(Long newsId) {
        return newsRepository.findById(newsId)
                .orElseThrow(() -> new NewsNotFoundException(newsId));
    }
    
    @Override
    public News getNewsByTitle(String title) {
        return newsRepository.findNewsByTitle(title)
                .orElseThrow(() -> new NewsNotFoundException("News NOT Found!"));
    }
    
    @Override
    public ApiResponse deleteNewsById(Long newsId) {
        News newsById = getNewsById(newsId);
        newsRepository.delete(newsById);
        return new ApiResponse("News Deleted Successfully!", true);
    }
    
    @Override
    public News updateNews(News news, MultipartFile file) {
        News newsById = getNewsById(news.getNewsId());
        
        if (file.isEmpty()) {
            news.setImage(newsById.getImage());
        } else {
            String image = getImageToString(file);
            news.setImage(image);
        }
        return newsRepository.save(news);
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
