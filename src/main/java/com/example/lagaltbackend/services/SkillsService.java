package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.Skills;
import com.example.lagaltbackend.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {

    @Autowired
    private SkillsRepository skillsRepository;

    /**
     * Get all Skills.
     *
     * @return - all skills.
     */
    public ResponseEntity<List<Skills>> getAllSkills() {
        List<Skills> data = skillsRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }

    /**
     * Get specific skill based on id.
     *
     * @param skillsId - Skills id
     * @return - Info about that specific skill.
     */
    public ResponseEntity<Skills> getSkillBySkillId(Long skillsId) {
        HttpStatus status;
        Skills skill = new Skills();
        if (!skillsRepository.existsById(skillsId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(skill, status);
        }
        skill = skillsRepository.findById(skillsId).get();
        status = HttpStatus.OK;
        return new ResponseEntity<>(skill, status);
    }
}
