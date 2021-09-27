package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.*;
import com.example.lagaltbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    /**
     * Get All Roles
     * @return - all roles
     */

    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> data = rolesRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }

    /**
     * Gets specific role based on id.
     * @param id - Roles id
     * @return - specific role
     */

    public ResponseEntity<Roles> getRoleById(Long id) {
        Optional<Roles> roles = rolesRepository.findById(id);
        return roles.map(role -> new ResponseEntity<>(role, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
