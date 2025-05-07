package com.foundation.service;

import com.foundation.entity.Team;
import com.foundation.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeamService {
    Team addTeam(Team team, MultipartFile file);
    
    List<Team> getAllTeam();
    
    Team getTeamById(Long teamId);
    
    ApiResponse deleteTeamById(Long teamId);
    
    Team updateTeam(Team team, MultipartFile file);
}
