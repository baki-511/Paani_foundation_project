package com.foundation.controller;

import com.foundation.entity.Team;
import com.foundation.payload.ApiResponse;
import com.foundation.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminTeamController {
    
    @Autowired
    private TeamService teamService;
    
    
    @GetMapping("/team/add")
    public String addTeam(Model model) {
        return "/admin/pages/teams/add-team";
    }
    
    @PostMapping("/team/add")
    public String addTeam(@ModelAttribute Team team,
                              @RequestParam("imageFile") MultipartFile imageFile,
                          RedirectAttributes redirectAttributes) {
        Team savedTeam = teamService.addTeam(team, imageFile);
        if (savedTeam != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/teams/all";
    }
    
    @GetMapping("/teams/all")
    public String allTeam(Model model) {
        return allTeam(model, 0, 5);
    }
    
    @GetMapping("/teams/all/{page}")
    public String allTeam(Model model, @PathVariable int page, int size) {
      
        List<Team> allTeam = teamService.getAllTeam();
        model.addAttribute("teams", allTeam);
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allTeam.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<Team> subList = allTeam.subList(start, end);
        Page<Team> teamPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("teams", teamPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", teamPage.getTotalPages());
        model.addAttribute("totalItems", teamPage.getTotalElements());
        model.addAttribute("size", size);
        
        return "/admin/pages/teams/all-team";
    }
    
    @GetMapping("/team/edit/{teamId}")
    public String editTeam(@PathVariable Long teamId, Model model) {
        Team team = teamService.getTeamById(teamId);
        model.addAttribute("team", team);
        return "/admin/pages/teams/edit-team";
    }
    
    @PostMapping("/team/edit")
    public String editTeam(@ModelAttribute Team team,
                              @RequestParam("imageFile") MultipartFile imageFile,
                           RedirectAttributes redirectAttributes ) {
        Team updatedTeam = teamService.updateTeam(team, imageFile);
        if (updatedTeam != null) {
            redirectAttributes.addFlashAttribute("message", "Updated Successfully!");
        }
        return "redirect:/admin/teams/all";
    }
    
    @GetMapping("/team/delete/{teamId}")
    public String deleteTeam(@PathVariable Long teamId, RedirectAttributes redirectAttributes) {
        ApiResponse response = teamService.deleteTeamById(teamId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/teams/all";
    }
}
