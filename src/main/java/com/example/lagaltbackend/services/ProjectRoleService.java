package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.ProjectRoles;
import com.example.lagaltbackend.models.Projects;
import com.example.lagaltbackend.models.Roles;
import com.example.lagaltbackend.models.Users;
import com.example.lagaltbackend.repositories.ProjectRoleRepository;
import com.example.lagaltbackend.repositories.ProjectsRepository;
import com.example.lagaltbackend.repositories.RolesRepository;
import com.example.lagaltbackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProjectRoleService {

    @Autowired
    private ProjectRoleRepository projectRoleRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    /**
     * Gets ProjectRole By ProjectRoleId
     * @param id
     * @return - projectRole
     */
    public ResponseEntity<ProjectRoles> getProjectRoleByProjectRoleId(Long id) {
        HttpStatus status;
        ProjectRoles projectRole = new ProjectRoles();
        if (!projectRoleRepository.existsById(id)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(projectRole, status);
        }
        projectRole = projectRoleRepository.findById(id).get();
        status = HttpStatus.OK;
        return new ResponseEntity<>(projectRole, status);
    }

    /**
     * Adds Project Role to the database
     * @param projectId - the project id
     * @param userId - the user id
     * @param roleId - and the role id
     * @return - project Role
     */

    public ResponseEntity<ProjectRoles> addProjectRole(Long projectId, Long userId, Long roleId) {
        HttpStatus status;

        System.out.println("ADD PROJECT ROLE");
        System.out.println("userId: " + userId);
        Users user = usersRepository.findById(userId).get();

        Roles role = rolesRepository.findById(roleId).get();
        System.out.println("Role title: " + role.getTitle());

        System.out.println("projectId: " + projectId);
        Projects project = projectsRepository.findById(projectId).get();

        ArrayList<Projects> projects = new ArrayList<>();
        projects.add(project);

        ProjectRoles projectRole = new ProjectRoles();
        projectRole.setUser(user);
        projectRole.setProjects(projects);
        projectRole.setRole(role);

        status = HttpStatus.CREATED;
        projectRole = projectRoleRepository.save(projectRole);
        return new ResponseEntity<>(projectRole, status);
    }
}
