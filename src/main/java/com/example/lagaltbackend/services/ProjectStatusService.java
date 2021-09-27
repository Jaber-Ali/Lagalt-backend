package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.ProjectStatus;
import com.example.lagaltbackend.repositories.ProjectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectStatusService {

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    /**
     * Get all project statuses
     *
     * @return - all project statuses
     */
    public ResponseEntity<List<ProjectStatus>> getAllProjectStatuses() {
        List<ProjectStatus> data = projectStatusRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }

    /**
     * Get project status by project id.
     *
     * @param id -Project Status id
     * @return - info about that specific project status
     */
    public ResponseEntity<ProjectStatus> getProjectStatusByProjectStatusId(Long id) {
        HttpStatus status;
        ProjectStatus projectStatus = new ProjectStatus();
        if (!projectStatusRepository.existsById(id)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(projectStatus, status);
        }
        projectStatus = projectStatusRepository.findById(id).get();
        status = HttpStatus.OK;
        return new ResponseEntity<>(projectStatus, status);
    }
}
