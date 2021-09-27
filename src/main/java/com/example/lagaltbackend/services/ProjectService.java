package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.Categories;
import com.example.lagaltbackend.models.Projects;
import com.example.lagaltbackend.models.Skills;
import com.example.lagaltbackend.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectsRepository projectsRepository;

    public ResponseEntity<Projects> saveProjectToDatabase(String title, String description, MultipartFile file, Date createdDate, Long categoryId, List<String> skills) {

        HttpStatus status;
        Categories category = new Categories();
        category.setId(categoryId);

        Projects project = new Projects();
        project.setTitle(title);
        project.setDescription(description);
        project.setCreatedDate(createdDate);
        project.setCategory(category);

        System.out.println(skills);
        ArrayList<Skills> skillsList = new ArrayList<>();
        for (String skill: skills) {
            Skills newSkill = new Skills();
            newSkill.setId(Long.parseLong(skill));
            skillsList.add(newSkill);
        }

        project.setSkills(skillsList);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName == null) {
            System.out.println("Not a valid file.");
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        } else {
            try {
//                project.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
                project.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException exception) {
                exception.printStackTrace();
                status = HttpStatus.BAD_REQUEST;
            }
        }

        status = HttpStatus.CREATED;
        project = projectsRepository.save(project);
        return new ResponseEntity<>(project, status);
    }
}
