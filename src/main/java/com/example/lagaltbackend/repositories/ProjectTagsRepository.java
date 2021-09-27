package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.ProjectTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTagsRepository extends JpaRepository<ProjectTags, Long> {
}
