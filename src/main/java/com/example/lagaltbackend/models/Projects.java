package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true)
    @Type(type = "text")
    @Lob
    private String image;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /*@ManyToMany
    @JoinTable(
            name = "users_projects",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    public List<Users> users;

    @JsonGetter("users")
    public List<String> users() {
        if (users != null) {
            return users.stream()
                    .map(user -> {
                        return "/api/v1/users/i/" + user.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }*/

    @ManyToMany
    @JoinTable(
            name = "projects_tags",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_tag_id")}
    )
    public List<ProjectTags> projectTags;

    @ManyToMany
    @JoinTable(
            name = "project_skills",
            joinColumns = {@JoinColumn(name = "skill_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    public List<Skills> skills;

    /**
     * @return a map of skill objects
     */
    @JsonGetter("skills")
    public List<String> skills() {
        if (skills != null) {
            return skills.stream()
                    .map(skill -> {
                        return "/api/v1/skills/" + skill.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    @ManyToMany
    @JoinTable(
            name = "projects_applications",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_application_id")}
    )
    public List<ProjectApplications> projectApplications;

    @ManyToMany
    @JoinTable(
            name = "projects_user_history",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_history_id")}
    )
    public List<UserHistory> userHistoryList;

    @ManyToMany
    @JoinTable(
            name = "projects_roles",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_roles_id")}
    )
    public List<ProjectRoles> projectRoles;

    /**
     * @return a list of project role urls
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

    @ManyToOne
    @JoinColumn(name = "project_status_id")
    private ProjectStatus projectStatus;

    /**
     * @return a projectStatus url
     */
    @JsonGetter("projectStatus")
    public String projectStatus() {
        if (projectStatus != null) {
            return "/api/v1/projectstatus/" + projectStatus.getId();
        }
        return null;
    }


    @OneToOne(mappedBy = "project")
    private DiscussionBoard discussionBoard;
    /**
     * @return a discussionBoard object
     */
    @JsonGetter("discussionBoard")
    public String discussionBoard() {
        if (discussionBoard != null) {
            return "/api/v1/discussionBoard/" + discussionBoard.getId();
        }
        return null;
    }

    @OneToOne(mappedBy = "project")
    private ChatBoard chatBoard;
    /**
     * @return a chatBoard object
     */
    @JsonGetter("chatBoard")
    public String chatBoard() {
        if (chatBoard != null) {
            return "/api/v1/chatBoard/" + chatBoard.getId();
        }
        return null;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Categories category;

    /**
     * @return a map of portfolio objects
     */
    @JsonGetter("category")
    public String category() {
        if (category != null) {
            return "/api/v1/categories/" + category.getId();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

/*
    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }*/

    public List<ProjectTags> getProjectTags() {
        return projectTags;
    }

    public void setProjectTags(List<ProjectTags> projectTags) {
        this.projectTags = projectTags;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

    public List<ProjectApplications> getProjectApplications() {
        return projectApplications;
    }

    public void setProjectApplications(List<ProjectApplications> projectApplications) {
        this.projectApplications = projectApplications;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<UserHistory> getUserHistoryList() {
        return userHistoryList;
    }

    public void setUserHistoryList(List<UserHistory> userHistoryList) {
        this.userHistoryList = userHistoryList;
    }

    public List<ProjectRoles> getProjectRoles() {
        return projectRoles;
    }

    public void setProjectRoles(List<ProjectRoles> projectRoles) {
        this.projectRoles = projectRoles;
    }

    public DiscussionBoard getDiscussionBoard() {
        return discussionBoard;
    }

    public void setDiscussionBoard(DiscussionBoard discussionBoard) {
        this.discussionBoard = discussionBoard;
    }

    public ChatBoard getChatBoard() {
        return chatBoard;
    }

    public void setChatBoard(ChatBoard chatBoard) {
        this.chatBoard = chatBoard;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
