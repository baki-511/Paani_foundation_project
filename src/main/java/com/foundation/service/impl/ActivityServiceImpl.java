package com.foundation.service.impl;

import com.foundation.entity.Activity;
import com.foundation.exception.ActivityNotFoundException;
import com.foundation.payload.ApiResponse;
import com.foundation.repository.ActivityRepository;
import com.foundation.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    
    @Override
    public Activity addActivity(Activity activity, MultipartFile file) {
        String image = getImageToString(file);
        activity.setImage(image);
        return activityRepository.save(activity);
    }
    
    @Override
    public List<Activity> getAllActivity() {
        return activityRepository.findAll();
    }
    
    @Override
    public Activity getActivityById(Long activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException(activityId));
    }
    
    @Override
    public ApiResponse deleteActivityById(Long activityId) {
        Activity activityById = getActivityById(activityId);
        activityRepository.delete(activityById);
        return new ApiResponse("Activity Deleted Successfully!", true);
    }
    
    @Override
    public Activity updateActivity(Activity activity, MultipartFile file) {
        Activity activityById = getActivityById(activity.getActivityId());
        
        if (file.isEmpty()) {
            activity.setImage(activityById.getImage());
        } else {
            String image = getImageToString(file);
            activity.setImage(image);
        }
        return activityRepository.save(activity);
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
