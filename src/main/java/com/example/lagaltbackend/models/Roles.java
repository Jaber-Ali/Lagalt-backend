package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    //@OneToOne(mappedBy = "role")
    //private ProjectRoles projectRole;
    @OneToMany(mappedBy = "role")
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
}
