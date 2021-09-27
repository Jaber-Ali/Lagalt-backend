package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ChatBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Projects project;
    /**
     * @return a project object
     */
    @JsonGetter("project")
    public String project() {
        if (project != null) {
            return "/api/v1/projects/" + project.getId();
        }
        return null;
    }

    @OneToMany(mappedBy = "chatBoard")
    private List<ChatMessages> chatMessages;

    @JsonGetter("chatMessages")
    public List<String> portfolios() {
        if (chatMessages != null) {
            return chatMessages.stream()
                    .map(chatMessage -> {
                        return "/api/v1/chatmessages/" + chatMessage.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public List<ChatMessages> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessages> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
