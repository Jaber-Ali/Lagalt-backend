package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.ChatMessages;
import com.example.lagaltbackend.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/", "http://localhost:3000/", "https://lagalt-chat.herokuapp.com/"})
@RequestMapping("/api/v1/chatmessages")

public class ChatMessagesController {

    @Autowired
    private ChatMessageService chatMessageService;

    /**
     * Get all ChatMessages in the database
     *
     * @return list of ChatMessages
     */
    @GetMapping
    public ResponseEntity<List<ChatMessages>> getAllChatMessages() {
        return chatMessageService.getAllChatMessages();
    }

    @GetMapping("/chatboard/{id}")
    public ResponseEntity<List<ChatMessages>> getChatMessageBasedOnChatboardId(@PathVariable("id") Long chatBoardId) {
        return chatMessageService.getChatMessagesByChatBoardId(chatBoardId);
    }

    /**
     * Get a specific ChatMessage by id
     *
     * @param id - ChatMessage id
     * @return -  ChatMessage
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChatMessages> getChatMessageById(@PathVariable Long id) {
        return chatMessageService.getChatMessageById(id);
    }

    /**
     * @param message
     * @param timestamp
     * @param keycloakEmail
     * @return
     */
    @PostMapping
    public ResponseEntity<ChatMessages> addChatMessages(@RequestParam("projectId") Long projectId,
                                                        @RequestParam("message") String message,
                                                        @RequestParam("timestamp") Date timestamp,
                                                        @RequestParam("keycloakEmail") String keycloakEmail) {

        return chatMessageService.addChatMessages(projectId, message, timestamp, keycloakEmail);
    }

    /**
     * Update existing ChatMessage
     *
     * @param chatMessage - the new ChatMessage object
     * @param id          - the ChatMessage id
     * @return the updated ChatMessage.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ChatMessages> updateChatMessages(@RequestBody ChatMessages chatMessage, @PathVariable Long id) {
        return chatMessageService.updateChatMessages(chatMessage, id);
    }

    /**
     * Deletes ChatMessage from the database
     *
     * @param id - ChatMessage id
     * @return - the table without the deleted ChatMessage.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteChatMessages(@PathVariable("id") Long id) {
        return chatMessageService.deleteChatMessages(id);
    }


}
