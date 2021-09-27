package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.ChatBoard;
import com.example.lagaltbackend.models.Projects;
import com.example.lagaltbackend.repositories.ChatBoardRepository;
import com.example.lagaltbackend.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChatBoardService {

    @Autowired
    private ChatBoardRepository chatBoardRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    /**
     * Get all chatBoards in the database
     *
     * @return list of chatBoards
     */

    public ResponseEntity<List<ChatBoard>> getAllChatBoard() {
        List<ChatBoard> data = chatBoardRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }
    /**
     * Get a specific chatBoard by id
     *
     * @param id - chatBoard id
     * @return -  chatBoard
     */

    public ResponseEntity<ChatBoard> getChatBoardById(@PathVariable Long id) {
        Optional<ChatBoard> chatBoardData = chatBoardRepository.findById(id);
        return chatBoardData.map(chatBoard -> new ResponseEntity<>(chatBoard, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update existing chatBoard
     *
     * @param chatBoard - the new chatBoard object
     * @param id        - the chatBoard id
     * @return the updated chatBoard.
     */
    public ResponseEntity<ChatBoard> updateChatBoard(ChatBoard chatBoard, Long id) {
        HttpStatus status;
        ChatBoard addChatBoard = new ChatBoard();
        if (!Objects.equals(id, chatBoard.getId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(addChatBoard, status);
        }
        addChatBoard = chatBoardRepository.save(chatBoard);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(addChatBoard, status);
    }

    /**
     * Deletes chatBoard from the database
     *
     * @param id - chatBoard id
     * @return - the database without the deleted chatBoard.
     */
    public ResponseEntity<HttpStatus> deleteChatBoard(Long id) {
        try {
            chatBoardRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add chatBoard to new project.
     * @param projectId - Project id
     * @return - chatBoard
     */

    public ResponseEntity<ChatBoard> addChatBoardToNewProject(Long projectId) {

        Projects project = new Projects();
        project.setId(projectId);

        ChatBoard chatBoard = new ChatBoard();
        chatBoard.setChatMessages(null);
        chatBoard.setProject(project);

        chatBoard = chatBoardRepository.save(chatBoard);
        return new ResponseEntity<>(chatBoard, HttpStatus.CREATED);
    }
}
