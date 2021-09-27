package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.ProjectRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRoleRepository extends JpaRepository<ProjectRoles, Long> {
}
