package com.foundation.service.impl;

import com.foundation.entity.Banner;
import com.foundation.exception.BannerNotFoundException;
import com.foundation.payload.ApiResponse;
import com.foundation.repository.BannerRepository;
import com.foundation.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;
    
    
    @Override
    public Banner addBanner(Banner blog, MultipartFile file) {
        String image = getBannerImage(file);
        blog.setImage(image);
        return bannerRepository.save(blog);
    }
    
    @Override
    public List<Banner> getAllBanner() {
        return bannerRepository.findAll();
    }
    
    @Override
    public Banner getBannerById(Integer bannerId) {
        return bannerRepository.findById(bannerId)
                .orElseThrow(()->new BannerNotFoundException(bannerId));
    }
    
    @Override
    public ApiResponse deleteBannerById(Integer bannerId) {
        Banner bannerById = getBannerById(bannerId);
        bannerRepository.delete(bannerById);
        return new ApiResponse("Banner Deleted Successfully!", true);
    }
    
    @Override
    public Banner updateBanner(Banner banner, MultipartFile file) {
        Banner bannerById = getBannerById(banner.getBannerId());
        if (file.isEmpty()) {
            banner.setImage(bannerById.getImage());
        } else {
            banner.setImage(getBannerImage(file));
        }
        return bannerRepository.save(banner);
    }
    
    
    private String getBannerImage(MultipartFile file) {
        String image = "";
        try {
            image = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
