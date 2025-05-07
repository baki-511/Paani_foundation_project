package com.foundation.service;

import com.foundation.entity.News;
import com.foundation.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    News addNews(News news, MultipartFile file);
    
    List<News> getAllNews();
    
    News getNewsById(Long newsId);
    
    News getNewsByTitle(String title);
    
    ApiResponse deleteNewsById(Long newsId);
    
    News updateNews(News news, MultipartFile file);
}
