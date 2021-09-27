package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class DiscussionBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_id")
    private  Projects project;
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

    @OneToMany(mappedBy = "discussionBoard")
    private List<DiscussionMessages> discussionMessages;

    @JsonGetter("discussionMessages")
    public List<String> portfolios() {
        if (discussionMessages != null) {
            return discussionMessages.stream()
                    .map(discussionMessage -> {
                        return "/api/v1/discussionMessages/" + discussionMessage.getId();
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

    public List<DiscussionMessages> getDiscussionMessages() {
        return discussionMessages;
    }

    public void setDiscussionMessages(List<DiscussionMessages> messages) {
        this.discussionMessages = messages;
    }
}
