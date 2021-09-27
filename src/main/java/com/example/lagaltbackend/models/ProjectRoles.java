package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ProjectRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToOne
    //@JoinColumn(name = "role_id")
    //private Roles role;
    @ManyToOne
    @JoinColumn(name = "role_id")
    public Roles role;

    @JsonGetter("role")
    public String role() {
        if (role != null) {
            return "/api/v1/roles/" + role.getId();
        }
        return null;
    }

    /*@OneToOne
    @JoinColumn(name = "user_id")
    private Users user;*/
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

    @ManyToMany
    @JoinTable(
            name = "projects_roles",
            joinColumns = {@JoinColumn(name = "project_roles_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    public List<Projects> projects;

    /**
     * @return a map of project objects
     */
    @JsonGetter("projects")
    public List<String> projects() {
        if (projects != null) {
            return projects.stream()
                    .map(project -> {
                        return "/api/v1/projects/" + project.getId();
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }
}
