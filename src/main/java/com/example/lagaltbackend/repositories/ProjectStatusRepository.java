package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long> {
}
