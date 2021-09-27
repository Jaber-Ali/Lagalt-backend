package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.Skills;
import com.example.lagaltbackend.services.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "https://lagalt-frontend-gbg.herokuapp.com/",
        "http://localhost:3000/",
        "http://localhost:3000/profile",
})
@RequestMapping("/api/v1/skills")

public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    /**
     * Get all portfolio items.
     *
     * @return - all portfolio items.
     */
    @GetMapping
    public ResponseEntity<List<Skills>> getAllSkills() {
        return skillsService.getAllSkills();
    }

    /**
     * Get specific skill based on id.
     *
     * @param skillsId - Skills id
     * @return - Info about that specific skill.
     */
    @GetMapping("/{skillsId}")
    public ResponseEntity<Skills> getSkillBySkillId(@PathVariable Long skillsId) {
        return skillsService.getSkillBySkillId(skillsId);
    }
}
