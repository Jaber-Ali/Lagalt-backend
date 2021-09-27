package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.ChatBoard;
import com.example.lagaltbackend.models.ChatMessages;
import com.example.lagaltbackend.models.Users;
import com.example.lagaltbackend.repositories.ChatBoardRepository;
import com.example.lagaltbackend.repositories.ChatMessagesRepository;
import com.example.lagaltbackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessagesRepository chatMessagesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ChatBoardRepository chatBoardRepository;

    /**
     * Get all ChatMessages in the database
     *
     * @return list of ChatMessages
     */
    public ResponseEntity<List<ChatMessages>> getAllChatMessages() {
        List<ChatMessages> data = chatMessagesRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }
    /**
     * Get a specific ChatMessage by id
     *
     * @param id - ChatMessage id
     * @return -  ChatMessage
     */
    public ResponseEntity<ChatMessages> getChatMessageById(Long id) {
        Optional<ChatMessages> ChatMessagesData = chatMessagesRepository.findById(id);
        return ChatMessagesData.map(ChatMessage -> new ResponseEntity<>(ChatMessage, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * Add Chat Messages
     * @param message
     * @param timestamp
     * @param keycloakEmail
     * @return - chatMessage
     */
    public ResponseEntity<ChatMessages> addChatMessages(Long projectId, String message, Date timestamp, String keycloakEmail) {
        System.out.println("ADD NEW CHAT MESSAGE");
        System.out.println("projectId: " + projectId);

        ChatMessages chatMessage = new ChatMessages();

        chatMessage.setMessage(message);
        chatMessage.setTimestamp(timestamp);

        // Save the correct user.
        List<Users> users =  usersRepository.findByKeycloakEmail(keycloakEmail);
        System.out.println("UserId: " + users.get(0).getId());
        chatMessage.setUser(users.get(0));

        // Save to the correct chatboard.
        List<ChatBoard> chatBoards = chatBoardRepository.findByProjectId(projectId);
        System.out.println("ChatBoardId: " + chatBoards.get(0).getId());
        chatMessage.setChatBoard(chatBoards.get(0));

        chatMessage = chatMessagesRepository.save(chatMessage);
        HttpStatus status;
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(chatMessage, status);
    }

    /**
     * Update existing ChatMessages
     *
     * @param ChatMessages - the new ChatMessage object
     * @param id        - the ChatMessage id
     * @return the updated ChatMessage.
     */
    public ResponseEntity<ChatMessages> updateChatMessages(ChatMessages ChatMessages, Long id) {
        HttpStatus status;
        ChatMessages addChatMessages = new ChatMessages();
        if (!Objects.equals(id, ChatMessages.getId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(addChatMessages, status);
        }
        addChatMessages = chatMessagesRepository.save(ChatMessages);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(addChatMessages, status);
    }

    /**
     * Deletes ChatMessage from the database
     *
     * @param id - ChatMessage id
     * @return - the database without the deleted ChatMessage.
     */
    public ResponseEntity<HttpStatus> deleteChatMessages(Long id) {
        try {
            chatMessagesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * gets chatMessages by chatBoard
     * @param chatBoardId - chatBoard id
     * @return - chatMessages
     */

    public ResponseEntity<List<ChatMessages>> getChatMessagesByChatBoardId(Long chatBoardId) {
        List<ChatMessages> chatMessages = chatMessagesRepository.findByChatBoardId(chatBoardId);
        return new ResponseEntity<>(chatMessages, HttpStatus.OK);
    }
}
