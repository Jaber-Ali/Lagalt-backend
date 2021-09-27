package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.ProjectStatus;
import com.example.lagaltbackend.services.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/", "http://localhost:3000/"})
@RequestMapping("/api/v1/projectstatus")
public class ProjectStatusController {

    @Autowired
    private ProjectStatusService projectStatusService;

    /**
     * Get all project statuses
     *
     * @return - all project statuses
     */
    @GetMapping
    public ResponseEntity<List<ProjectStatus>> getAllProjectStatuses() {
        return projectStatusService.getAllProjectStatuses();
    }

    /**
     * Get project status by project id.
     *
     * @param id -Project Status id
     * @return - info about that specific project status
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectStatus> getProjectStatusByProjectStatusId(@PathVariable Long id) {
        return projectStatusService.getProjectStatusByProjectStatusId(id);
    }

}
