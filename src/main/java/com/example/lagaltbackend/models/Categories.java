package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "category")
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

    @OneToMany(mappedBy = "category")
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

    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
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
}
