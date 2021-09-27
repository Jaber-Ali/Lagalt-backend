package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import javax.sound.sampled.Port;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String keycloakEmail;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    /*@ManyToMany
    @JoinTable(
            name = "users_projects",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )*/
    /*@ManyToMany(mappedBy = "users")
    public List<Projects> projects;

    @JsonGetter("projects")
    public List<String> projects() {
        if (projects != null) {
            return projects.stream()
                    .map(project -> {
                        return "/api/v1/projects/" + project.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }*/

    @OneToMany(mappedBy = "user")
    public List<UserHistory> userHistoryList;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @JsonGetter("userProfile")
    public String userProfile() {
        if (userProfile != null) {
            return "/api/v1/userProfile/" + userProfile.getId();
        }
        return null;
    }

    /*@OneToOne(mappedBy = "user")
    private ProjectRoles projectRole;*/

    @OneToMany(mappedBy = "user")
    public List<ProjectRoles> projectRoles;

    /**
     * @return a map of project objects
     */
    @JsonGetter("projectRoles")
    public List<String> projectRoles() {
        if (projectRoles != null) {
            return projectRoles.stream()
                    .map(projectRole -> {
                        return "/api/v1/projectrole/" + projectRole.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }
    /*
    @JsonGetter("projectRole")
    public String projectRole() {
        if (projectRole != null) {
            return "/api/v1/projectrole/" + projectRole.getId();
        }
        return null;
    }*/

    @OneToMany(mappedBy = "user")
    private List<PortfolioItem> portfolioList;

    /**
     * @return a map of portfolio objects
     */
    @JsonGetter("portfolioList")
    public List<String> portfolios() {
        if (portfolioList != null) {
            return portfolioList.stream()
                    .map(portfolioItem -> {
                        return "/api/v1/portfolio/" + portfolioItem.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }


    @OneToMany(mappedBy = "user")
    private List<DiscussionMessages> discussionMessages;

    /**
     * @return a map of portfolio objects
     */
    @JsonGetter("discussionMessages")
    public List<String> discussionMessages() {
        if (discussionMessages != null) {
            return discussionMessages.stream()
                    .map(discussionMessage -> {
                        return "/api/v1/discussionMessages/" + discussionMessage.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    @OneToMany(mappedBy = "user")
    private List<ChatMessages> chatMessages;

    /**
     * @return a map of portfolio objects
     */
    @JsonGetter("chatMessages")
    public List<String> chatMessages() {
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

    public String getKeycloakEmail() {
        return keycloakEmail;
    }

    public void setKeycloakEmail(String keycloakEmail) {
        this.keycloakEmail = keycloakEmail;
    }

    public List<DiscussionMessages> getDiscussionMessages() {
        return discussionMessages;
    }

    public void setDiscussionMessages(List<DiscussionMessages> discussionMessages) {
        this.discussionMessages = discussionMessages;
    }

    public List<ChatMessages> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessages> chatMessages) {
        this.chatMessages = chatMessages;
    }

    /*public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }*/

    public List<UserHistory> getUserHistoryList() {
        return userHistoryList;
    }

    public void setUserHistoryList(List<UserHistory> userHistoryList) {
        this.userHistoryList = userHistoryList;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    /*
    public ProjectRoles getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(ProjectRoles projectRole) {
        this.projectRole = projectRole;
    }*/

    public List<ProjectRoles> getProjectRoles() {
        return projectRoles;
    }

    public void setProjectRoles(List<ProjectRoles> projectRoles) {
        this.projectRoles = projectRoles;
    }

    public List<PortfolioItem> getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(List<PortfolioItem> portfolioList) {
        this.portfolioList = portfolioList;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
