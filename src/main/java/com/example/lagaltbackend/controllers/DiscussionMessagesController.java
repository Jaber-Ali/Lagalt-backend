
package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.DiscussionMessages;
import com.example.lagaltbackend.services.DiscussionMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/", "http://localhost:3000/"})
@RequestMapping("/api/v1/discussionMessages")

public class DiscussionMessagesController {
    @Autowired
    private DiscussionMessageService discussionMessageService;

    /**
     * Get all discussionMessages in the database
     *
     * @return list of discussionMessages
     */
    @GetMapping
    public ResponseEntity<List<DiscussionMessages>> getAllDiscussionMessages() {
        return discussionMessageService.getAllDiscussionMessages();
    }

    /**
     * Get a specific DiscussionMessages by id
     *
     * @param id - DiscussionMessages id
     * @return -  DiscussionMessages
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiscussionMessages> getDiscussionMessageById(@PathVariable Long id) {
        return discussionMessageService.getDiscussionMessageById(id);
    }

    /**
     * Add a discussionMessage to the database
     *
     * @param message           the message text
     * @param timestamp         time when message is sent
     * @param userId            id of user that sent message
     * @param discussionBoardId the discutionBoard message is connected to
     * @return - the created discussionMessage
     */
    @PostMapping
    public ResponseEntity<DiscussionMessages> addDiscussionMessage(@RequestParam("message") String message,
                                                                   @RequestParam("timestamp") Date timestamp,
                                                                   @RequestParam("user_id") Long userId,
                                                                   @RequestParam("discussion_board_id") Long discussionBoardId) {
        return discussionMessageService.addDiscussionMessage(message, timestamp, userId, discussionBoardId);
    }

    /**
     * Update existing discussionMessage
     *
     * @param discussionMessage - the new discussionMessage object
     * @param id                - the discussionMessage id
     * @return the updated discussionMessage.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DiscussionMessages> updateDiscussionMessage(@RequestBody DiscussionMessages discussionMessage, @PathVariable Long id) {
        return updateDiscussionMessage(discussionMessage, id);
    }

    /**
     * Deletes discussionMessage from the database
     *
     * @param id - discussionMessage id
     * @return - the database without the deleted discussionMessage.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDiscussionMessage(@PathVariable("id") Long id) {
        return discussionMessageService.deleteDiscussionMessage(id);
    }

}
