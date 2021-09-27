package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.DiscussionBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionBoardRepository extends JpaRepository<DiscussionBoard, Long> {
}

