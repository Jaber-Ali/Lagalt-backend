package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.Projects;
import com.example.lagaltbackend.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/", "http://localhost:3000/"})
@RequestMapping("/api/v1/projects")

public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;

    /**
     * Get all projects in the database
     *
     * @return list of projects
     */
    @GetMapping
    public ResponseEntity<List<Projects>> getAllProjects() {
        return projectsService.getAllProjects();
    }

    /**
     * Get a specific Project by id
     *
     * @param id - Project id
     * @return -  specific Project based on id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Projects> getProjectById(@PathVariable Long id) {
        return projectsService.getProjectById(id);
    }

    /**
     * Get a specific Project by id
     *
     * @param id - Project id
     * @return -  Projects
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Projects>> getProjectByCategory(@PathVariable Long id) {
        return projectsService.getProjectByCategory(id);
    }

    /**
     * Fetches recommended projects form the database
     *
     * @return A lost of recommended projects
     */
    @GetMapping("/recommended")
    public ResponseEntity<List<Projects>> getRecommendedProjects() {
        return projectsService.getRecommendedProjects();
    }

    /**
     * filters the projects based on search query and category
     *
     * @param title      the search query
     * @param categoryId the category you want to filter on
     * @return a list of the filtered projects
     */
    @GetMapping("/filter/{title},{categoryId}")
    public ResponseEntity<List<Projects>> findProjects(@PathVariable String title, @PathVariable Long categoryId) {
                                                       /*@RequestParam("title") String title,
                                                       @RequestParam("categoryId") Long categoryId*/

        return projectsService.findProjects(title, categoryId);
    }

    /**
     * Adds a project to the database
     *
     * @param title       title of project
     * @param description description of project
     * @param file        image of project
     * @param createdDate date when project is created
     * @param categoryId  what category the project have
     * @param skills      The different skills connected to the project
     * @return The created project
     */
    @PostMapping()
    public ResponseEntity<Projects> addProject(@RequestParam("title") String title,
                                               @RequestParam("description") String description,
                                               @RequestParam("image") MultipartFile file,
                                               @RequestParam("createdDate") Date createdDate,
                                               @RequestParam("category") Long categoryId,
                                               @RequestParam("skills") List<String> skills) {
        return projectsService.saveProjectToDatabase(title, description, file, createdDate, categoryId, skills);
    }

    /**
     * Update existing project
     *
     * @param title           updated title of project
     * @param description     updated project description
     * @param projectStatusId the updated project status
     * @param id              - the project id
     * @return the updated project.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Projects> updateProject(@RequestParam("title") String title,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("projectStatusId") Long projectStatusId, @PathVariable Long id) {
        return projectsService.updateProject(id, title, description, projectStatusId);
    }

    /**
     * Deletes project from the database
     *
     * @param id - project id
     * @return - the database without the deleted project.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") Long id) {
        return projectsService.deleteProject(id);
    }

}
