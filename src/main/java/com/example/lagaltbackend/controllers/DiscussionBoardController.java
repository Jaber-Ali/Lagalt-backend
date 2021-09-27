
package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.DiscussionBoard;
import com.example.lagaltbackend.models.DiscussionMessages;
import com.example.lagaltbackend.services.DiscussionBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/", "http://localhost:3000/"})
@RequestMapping("/api/v1/discussionBoard")

public class DiscussionBoardController {

    @Autowired
    private DiscussionBoardService discussionBoardService;

    /**
     * Get all discussionBoards in the database
     *
     * @return list of discussionBoards
     */
    @GetMapping
    public ResponseEntity<List<DiscussionBoard>> getAllDiscussionBoard() {
        return discussionBoardService.getAllDiscussionBoard();
    }

    /**
     * Get a specific DiscussionBoard by id
     *
     * @param id - DiscussionBoard id
     * @return -  DiscussionBoard
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiscussionBoard> getDiscussionBoardById(@PathVariable Long id) {
        return discussionBoardService.getDiscussionBoardById(id);
    }

    /**
     * adds a DiscussionBoard to the DB
     *
     * @param projectId id of project you want the DiscussionBoard to be connected to
     * @return the created DiscussionBoard
     */
    @PostMapping
    public ResponseEntity<DiscussionBoard> addDiscussionBoard(@RequestParam("projectId") Long projectId) {
        return discussionBoardService.addDiscussionBoardToNewProject(projectId);
    }

    /**
     * Update existing DiscussionBoard
     *
     * @param discussionBoard - the new DiscussionBoard object
     * @param id              - the DiscussionBoard id
     * @return the updated DiscussionBoard.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DiscussionBoard> updateDiscussionBoard(@RequestBody DiscussionBoard discussionBoard, @PathVariable Long id) {
        return discussionBoardService.updateDiscussionBoard(discussionBoard, id);
    }

    /**
     * Deletes DiscussionBoard from the database
     *
     * @param id - DiscussionBoard id
     * @return - the database without the deleted DiscussionBoard.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDiscussionBoard(@PathVariable("id") Long id) {
        return discussionBoardService.deleteDiscussionBoard(id);
    }

    @GetMapping("/boardMessages/{id}")
    public ResponseEntity<List<DiscussionMessages>> getDiscussionBoardMessages(@PathVariable Long id) {
        return discussionBoardService.getDiscussionBoardMessages(id);
    }
}
