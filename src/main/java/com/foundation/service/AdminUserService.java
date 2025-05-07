package com.foundation.service;

import com.foundation.entity.AdminUser;
import com.foundation.payload.ApiResponse;

public interface AdminUserService {
    AdminUser addUser(AdminUser adminUser);
    
    AdminUser getAdminUserById(Long userId);
    
    AdminUser getAdminUserByName(String name);
    
    ApiResponse deleteAdminUser(Long userId);
    
    AdminUser updateAdminUser(AdminUser adminUser);
}
