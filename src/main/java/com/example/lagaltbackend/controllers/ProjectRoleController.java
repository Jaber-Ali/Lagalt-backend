package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.ProjectRoles;
import com.example.lagaltbackend.services.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/", "http://localhost:3000/"})
@RequestMapping("/api/v1/projectrole")
public class ProjectRoleController {

    @Autowired
    private ProjectRoleService projectRoleService;

    /**
     * Get project role by id.
     *
     * @param id -Project role id
     * @return - Info about that specific project role
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectRoles> getProjectRoleByProjectRoleId(@PathVariable Long id) {
        return projectRoleService.getProjectRoleByProjectRoleId(id);
    }

    /**
     * Adds a ProjectRole to the DB
     *
     * @param projectId project connected to the role
     * @param userId    id of user that will get role
     * @param roleId    role id, i.e the role user will get in project
     * @return the created ProjectRoles
     */
    @PostMapping
    public ResponseEntity<ProjectRoles> addProjectRole(@RequestParam("projectId") Long projectId,
                                                       @RequestParam("userId") Long userId,
                                                       @RequestParam("roleId") Long roleId) {
        return projectRoleService.addProjectRole(projectId, userId, roleId);
    }

}
