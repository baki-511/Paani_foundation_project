package com.foundation.service;

import com.foundation.entity.MyGallery;
import com.foundation.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MyGalleryService {
    MyGallery addMyGallery(MyGallery myGallery, MultipartFile file);
    
    List<MyGallery> getAllMyGallery();
    
    MyGallery getMyGalleryById(Long myGalleryId);
    
    ApiResponse deleteMyGalleryById(Long myGalleryId);
    
    MyGallery updateMyGallery(MyGallery myGallery, MultipartFile file);
}
