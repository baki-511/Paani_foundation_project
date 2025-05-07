package com.foundation.service;

import com.foundation.entity.Activity;
import com.foundation.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ActivityService {
    Activity addActivity(Activity activity, MultipartFile file);
    
    List<Activity> getAllActivity();
    
    Activity getActivityById(Long activityId);
    
    ApiResponse deleteActivityById(Long activityId);
    
    Activity updateActivity(Activity activity, MultipartFile file);
}
