package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ChatMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String message;

    @Column()
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
    @JoinColumn(name = "chat_board_id")
    public ChatBoard chatBoard;

    @JsonGetter("chatBoard")
    public String chatBoard() {
        if (chatBoard != null) {
            return "/api/v1/chatboard/" + chatBoard.getId();
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

    public ChatBoard getChatBoard() {
        return chatBoard;
    }

    public void setChatBoard(ChatBoard chatBoard) {
        this.chatBoard = chatBoard;
    }
}
