package com.foundation.service.impl;

import com.foundation.entity.Team;
import com.foundation.exception.TeamNotFoundException;
import com.foundation.payload.ApiResponse;
import com.foundation.repository.TeamRepository;
import com.foundation.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    
    @Override
    public Team addTeam(Team team, MultipartFile file) {
        String image = getImageToString(file);
        team.setImage(image);
        return teamRepository.save(team);
    }
    
    @Override
    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }
    
    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException(teamId));
    }
    
    
    @Override
    public ApiResponse deleteTeamById(Long teamId) {
        Team teamById = getTeamById(teamId);
        teamRepository.delete(teamById);
        return new ApiResponse("Team Deleted Successfully!", true);
    }
    
    @Override
    public Team updateTeam(Team team, MultipartFile file) {
        Team teamById = getTeamById(team.getTeamId());
        
        if (file.isEmpty()) {
            team.setImage(teamById.getImage());
        } else {
            String image = getImageToString(file);
            team.setImage(image);
        }
        return teamRepository.save(team);
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
