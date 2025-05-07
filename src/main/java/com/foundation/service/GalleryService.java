package com.foundation.service;

import com.foundation.entity.Gallery;
import com.foundation.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {
    Gallery addGallery(MultipartFile file);
    
    List<Gallery> getAllGallery();
    
    Gallery getGalleryById(Long galleryId);
    
    ApiResponse deleteGalleryById(Long galleryId);
    
}
