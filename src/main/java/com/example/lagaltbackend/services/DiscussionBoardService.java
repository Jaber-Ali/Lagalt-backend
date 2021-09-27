package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.DiscussionBoard;
import com.example.lagaltbackend.models.DiscussionMessages;
import com.example.lagaltbackend.models.Projects;
import com.example.lagaltbackend.repositories.DiscussionBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DiscussionBoardService {

    @Autowired
    private DiscussionBoardRepository discussionBoardRepository;
    /**
     * Get all discussionBoards in the database
     *
     * @return list of discussionBoards
     */
    public ResponseEntity<List<DiscussionBoard>> getAllDiscussionBoard() {
        List<DiscussionBoard> data = discussionBoardRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }
    /**
     * Get a specific DiscussionBoard by id
     *
     * @param id - DiscussionBoard id
     * @return -  DiscussionBoard
     */
    public ResponseEntity<DiscussionBoard> getDiscussionBoardById(Long id) {
        Optional<DiscussionBoard> DiscussionBoardData = discussionBoardRepository.findById(id);
        return DiscussionBoardData.map(DiscussionBoard -> new ResponseEntity<>(DiscussionBoard, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update existing DiscussionBoard
     *
     * @param discussionBoard - the new DiscussionBoard object
     * @param id        - the DiscussionBoard id
     * @return the updated DiscussionBoard.
     */
    public ResponseEntity<DiscussionBoard> updateDiscussionBoard(DiscussionBoard discussionBoard, Long id) {
        HttpStatus status;
        DiscussionBoard addDiscussionBoard = new DiscussionBoard();
        if (!Objects.equals(id, discussionBoard.getId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(addDiscussionBoard, status);
        }
        addDiscussionBoard = discussionBoardRepository.save(discussionBoard);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(addDiscussionBoard, status);
    }

    /**
     * Deletes DiscussionBoard from the database
     *
     * @param id - DiscussionBoard id
     * @return - the database without the deleted DiscussionBoard.
     */
    public ResponseEntity<HttpStatus> deleteDiscussionBoard(Long id) {
        try {
            discussionBoardRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets DiscussionBoardMessages
     * @param id - DiscussionMessages id
     * @return - discussionMessages
     */

    public  ResponseEntity<List<DiscussionMessages>> getDiscussionBoardMessages(Long id){
        DiscussionBoard discussionBoard = discussionBoardRepository.findById(id).get();
        List<DiscussionMessages> discussionMessages = discussionBoard.getDiscussionMessages();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(discussionMessages, status);
    }

    /**
     * Adds DiscussionBoard to new project
     * @param projectId - project id
     * @return discussionBoard
     */
    public ResponseEntity<DiscussionBoard> addDiscussionBoardToNewProject(Long projectId) {

        Projects project = new Projects();
        project.setId(projectId);

        DiscussionBoard discussionBoard = new DiscussionBoard();
        discussionBoard.setDiscussionMessages(null);
        discussionBoard.setProject(project);

        discussionBoard = discussionBoardRepository.save(discussionBoard);
        return new ResponseEntity<>(discussionBoard, HttpStatus.CREATED);
    }
}
