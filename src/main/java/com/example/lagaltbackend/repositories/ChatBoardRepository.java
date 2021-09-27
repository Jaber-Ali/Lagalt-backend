package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.ChatBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatBoardRepository extends JpaRepository<ChatBoard, Long> {
    /**
     * Finds a ChatBoard with projectId
     *
     * @param projectId The projectId of the ChatBoard you want to fetch
     * @return the selected chatBoard
     */
    List<ChatBoard> findByProjectId(Long projectId);

}
