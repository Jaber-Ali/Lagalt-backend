package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.ChatBoard;
import com.example.lagaltbackend.services.ChatBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/", "http://localhost:3000/", "https://lagalt-chat.herokuapp.com/"})
@RequestMapping("/api/v1/chatBoard")

public class ChatBoardController {
    @Autowired
    private ChatBoardService chatBoardService;

    /**
     * Get all chatBoards in the database
     *
     * @return list of chatBoards
     */
    @GetMapping
    public ResponseEntity<List<ChatBoard>> getAllChatBoard() {
        return chatBoardService.getAllChatBoard();
    }

    /**
     * Get a specific chatBoard by id
     *
     * @param id - chatBoard id
     * @return -  chatBoard
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChatBoard> getChatBoardById(@PathVariable Long id) {
        return chatBoardService.getChatBoardById(id);
    }

    /**
     * Add a chatBoard to the database
     *
     * @param projectId - id of the project that chat board will be connected to
     * @return - the created chatBoard
     */
    @PostMapping
    public ResponseEntity<ChatBoard> addChatBoardToNewProject(@RequestParam("projectId") Long projectId) {
        return chatBoardService.addChatBoardToNewProject(projectId);
    }

    /**
     * Update existing chatBoard
     *
     * @param chatBoard - the new chatBoard object
     * @param id        - the chatBoard id
     * @return the updated chatBoard.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ChatBoard> updateChatBoard(@RequestBody ChatBoard chatBoard, @PathVariable Long id) {
        return chatBoardService.updateChatBoard(chatBoard, id);
    }

    /**
     * Deletes chatBoard from the database
     *
     * @param id - chatBoard id
     * @return - the database without the deleted chatBoard.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteChatBoard(@PathVariable("id") Long id) {
        return chatBoardService.deleteChatBoard(id);
    }

}
