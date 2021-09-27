package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.ChatMessages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessagesRepository extends JpaRepository<ChatMessages, Long> {

    /**
     * Fetches chatMessages based on ChatBoardId
     * @param chatBoardId The ChatBoardId of the chatMessages
     * @return the list of chatMessages
     */
    List<ChatMessages> findByChatBoardId(Long chatBoardId);

}
