package com.example.lagaltbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<PortfolioItem> portfolio;

    @Column(nullable = false)
    private Boolean isHidden;

    @Column(nullable = false)
    private String about;

    @ManyToMany
    @JoinTable(
            name = "user_profile_skills",
            joinColumns = {@JoinColumn(name = "user_profile_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    public List<Skills> skills;

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
            name = "user_applications",
            joinColumns = {@JoinColumn(name = "project_application_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_profile_id")}
    )
    public List<ProjectApplications> projectApplications;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<PortfolioItem> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<PortfolioItem> portfolio) {
        this.portfolio = portfolio;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


}
