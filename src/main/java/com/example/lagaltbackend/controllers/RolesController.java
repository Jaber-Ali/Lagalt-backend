package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.PortfolioItem;
import com.example.lagaltbackend.models.Roles;
import com.example.lagaltbackend.models.Skills;
import com.example.lagaltbackend.models.Users;
import com.example.lagaltbackend.services.RolesService;
import com.example.lagaltbackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/", "http://localhost:3000/"})
@RequestMapping("/api/v1/roles")

public class RolesController {

    @Autowired
    private RolesService rolesService;

    /**
     * Get all roles from the DB
     *
     * @return a list of all roles
     */
    @GetMapping
    public ResponseEntity<List<Roles>> getAllRoles() {
        return rolesService.getAllRoles();
    }

    /**
     * Get a role by id
     *
     * @param id id of role you want to get
     * @return the specific role
     */
    @GetMapping("/{id}")
    public ResponseEntity<Roles> getRoleById(@PathVariable("id") Long id) {
        return rolesService.getRoleById(id);
    }


}
