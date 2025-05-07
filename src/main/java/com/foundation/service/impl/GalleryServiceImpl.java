package com.foundation.service.impl;

import com.foundation.entity.Gallery;
import com.foundation.exception.GalleryNotFoundException;
import com.foundation.payload.ApiResponse;
import com.foundation.repository.GalleryRepository;
import com.foundation.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;
    
    
    @Override
    public Gallery addGallery(MultipartFile file) {
        String image = getImageToString(file);
        Gallery gallery = new Gallery();
        gallery.setImage(image);
        return galleryRepository.save(gallery);
    }
    
    @Override
    public List<Gallery> getAllGallery() {
        return galleryRepository.findAll();
    }
    
    @Override
    public Gallery getGalleryById(Long galleryId) {
        return galleryRepository.findById(galleryId)
                .orElseThrow(() -> new GalleryNotFoundException(galleryId));
    }
    
    @Override
    public ApiResponse deleteGalleryById(Long galleryId) {
        Gallery gallery = getGalleryById(galleryId);
        galleryRepository.delete(gallery);
        return new ApiResponse("Gallery Image Deleted Successfully!", true);
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
