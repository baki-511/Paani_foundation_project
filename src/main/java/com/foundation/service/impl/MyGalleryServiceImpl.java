package com.foundation.service.impl;

import com.foundation.entity.MyGallery;
import com.foundation.exception.GalleryNotFoundException;
import com.foundation.payload.ApiResponse;
import com.foundation.repository.MyGalleryRepository;
import com.foundation.service.MyGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class MyGalleryServiceImpl implements MyGalleryService {
    @Autowired
    private MyGalleryRepository myGalleryIdRepository;
    
    @Override
    public MyGallery addMyGallery(MyGallery myGalleryId, MultipartFile file) {
        String image = getImageToString(file);
        myGalleryId.setImage(image);
        return myGalleryIdRepository.save(myGalleryId);
    }
    
    @Override
    public List<MyGallery> getAllMyGallery() {
        return myGalleryIdRepository.findAll();
    }
    
    @Override
    public MyGallery getMyGalleryById(Long myGalleryIdId) {
        return myGalleryIdRepository.findById(myGalleryIdId)
                .orElseThrow(() -> new GalleryNotFoundException(myGalleryIdId));
    }
    
    @Override
    public ApiResponse deleteMyGalleryById(Long myGalleryIdId) {
        MyGallery myGalleryIdById = getMyGalleryById(myGalleryIdId);
        myGalleryIdRepository.delete(myGalleryIdById);
        return new ApiResponse("MyGallery Deleted Successfully!", true);
    }
    
    @Override
    public MyGallery updateMyGallery(MyGallery myGalleryId, MultipartFile file) {
        MyGallery myGalleryIdById = getMyGalleryById(myGalleryId.getGalleryId());
        
        if (file.isEmpty()) {
            myGalleryId.setImage(myGalleryIdById.getImage());
        } else {
            String image = getImageToString(file);
            myGalleryId.setImage(image);
        }
        return myGalleryIdRepository.save(myGalleryId);
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
