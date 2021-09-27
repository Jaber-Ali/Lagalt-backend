package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ProjectTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "projects_tags",
            joinColumns = {@JoinColumn(name = "project_tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    public List<Projects> projects;

    /**
     * @return a map of skill objects
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

    @Column()
    private String tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }
}
