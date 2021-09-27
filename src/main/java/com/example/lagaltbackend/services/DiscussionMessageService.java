package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.DiscussionBoard;
import com.example.lagaltbackend.models.DiscussionMessages;
import com.example.lagaltbackend.models.Users;
import com.example.lagaltbackend.repositories.DiscussionMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DiscussionMessageService {
    @Autowired
    private DiscussionMessagesRepository discussionMessagesRepository;

    /**
     * Get all discussionMessages in the database
     *
     * @return list of discussionMessages
     */
    public ResponseEntity<List<DiscussionMessages>> getAllDiscussionMessages() {
        List<DiscussionMessages> data = discussionMessagesRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }
    /**
     * Get a specific DiscussionMessages by id
     *
     * @param id - DiscussionMessages id
     * @return -  DiscussionMessages
     */
    public ResponseEntity<DiscussionMessages> getDiscussionMessageById(Long id) {
        Optional<DiscussionMessages> DiscussionMessagesData = discussionMessagesRepository.findById(id);
        return DiscussionMessagesData.map(discussionMessage -> new ResponseEntity<>(discussionMessage, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * Add a discussionMessage to the database
     *
     * @param
     * @return - the created discussionMessage
     */
    public ResponseEntity<DiscussionMessages> addDiscussionMessage(String message, Date timestamp, Long userId, Long discussionBoardId) {
        DiscussionMessages discussionMessage = new DiscussionMessages();
        discussionMessage.setMessage(message);
        discussionMessage.setTimestamp(timestamp);
        DiscussionBoard discussionBoard = new DiscussionBoard();
        discussionBoard.setId(discussionBoardId);
        Users user = new Users();
        user.setId(userId);
        discussionMessage.setDiscussionBoard(discussionBoard);
        discussionMessage.setUser(user);
        DiscussionMessages add = discussionMessagesRepository.save(discussionMessage);
        HttpStatus status;
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(add, status);
    }

    /**
     * Update existing discussionMessage
     *
     * @param discussionMessage - the new discussionMessage object
     * @param id        - the discussionMessage id
     * @return the updated discussionMessage.
     */
    public ResponseEntity<DiscussionMessages> updateDiscussionMessage(DiscussionMessages discussionMessage, Long id) {
        HttpStatus status;
        DiscussionMessages addDiscussionMessages = new DiscussionMessages();
        if (!Objects.equals(id, discussionMessage.getId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(addDiscussionMessages, status);
        }
        addDiscussionMessages = discussionMessagesRepository.save(discussionMessage);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(addDiscussionMessages, status);
    }

    /**
     * Deletes discussionMessage from the database
     *
     * @param id - discussionMessage id
     * @return - the database without the deleted discussionMessage.
     */
    public ResponseEntity<HttpStatus> deleteDiscussionMessage(Long id) {
        try {
            discussionMessagesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
