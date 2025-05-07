package com.foundation.service.impl;

import com.foundation.entity.AdminUser;
import com.foundation.exception.UserNotFoundException;
import com.foundation.payload.ApiResponse;
import com.foundation.repository.AdminUserRepository;
import com.foundation.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserRepository adminUserRepository;
    
    @Override
    public AdminUser addUser(AdminUser adminUser) {
        return adminUserRepository.save(adminUser);
    }
    
    @Override
    public AdminUser getAdminUserById(Long userId) {
        return adminUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
    
    @Override
    public AdminUser getAdminUserByName(String name) {
        return adminUserRepository.findByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User NOT Found!"));
    }
    
    @Override
    public ApiResponse deleteAdminUser(Long userId) {
        AdminUser adminUserById = getAdminUserById(userId);
        adminUserRepository.delete(adminUserById);
        return new ApiResponse("User Deleted Successfully!", true);
    }
    
    @Override
    public AdminUser updateAdminUser(AdminUser adminUser) {
        getAdminUserById(adminUser.getId());
        
        return adminUserRepository.save(adminUser);
    }
}
