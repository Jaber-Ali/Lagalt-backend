package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DiscussionMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @Column
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Users user;

    @JsonGetter("user")
    public String user() {
        if (user != null) {
            return "/api/v1/users/i/" + user.getId();
        }
        return null;
    }

    @ManyToOne
    @JoinColumn(name = "discussion_board_id")
    public DiscussionBoard discussionBoard;

    @JsonGetter("discussionBoard")
    public String discussionBoard() {
        if (discussionBoard != null) {
            return "/api/v1/discussionboard/" + discussionBoard.getId();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public DiscussionBoard getDiscussionBoard() {
        return discussionBoard;
    }

    public void setDiscussionBoard(DiscussionBoard discussionBoard) {
        this.discussionBoard = discussionBoard;
    }

}
