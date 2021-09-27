package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.*;
import com.example.lagaltbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @Autowired ProjectRoleRepository projectRoleRepository;

    /**
     * Get all projects in the database
     *
     * @return list of projects
     */
    public ResponseEntity<List<Projects>> getAllProjects() {
        List<Projects> data = projectsRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }
    /**
     * Get a specific Project by id
     *
     * @param id - Project id
     * @return -  Projects
     */
    public ResponseEntity<Projects> getProjectById(Long id) {
        Optional<Projects> projectsData = projectsRepository.findById(id);
        return projectsData.map(project -> new ResponseEntity<>(project, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * Get a specific Project by id
     *
     * @param id - Project id
     * @return -  Projects
     */
    public ResponseEntity<List<Projects>> getProjectByCategory(Long id) {
        Categories category = categoriesRepository.findById(id).get();
        List<Projects> projects = category.getProjects();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(projects, status);
    }

    public ResponseEntity<List<Projects>> findProjects(String title, Long id) {
        System.out.println(id);
        List<Projects> projects = projectsRepository.findProjectsList(title, id);
        //List<Projects> projects = category.getProjects();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(projects, status);
    }

    public ResponseEntity<List<Projects>> getRecommendedProjects() {
        List<Projects> projects = projectsRepository.getRecommendedProjects();
        //List<Projects> projects = category.getProjects();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(projects, status);
    }

//    @Override
//    public List<Projects> getRandomSelection(Criteria criteria, int userNumberOfElements) {
//        criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
//        criteria.setMaxResults(userNumberOfElements);
//        return criteria.list();
//    }



    public ResponseEntity<Projects> saveProjectToDatabase(String title, String description, MultipartFile file, Date createdDate, Long categoryId, List<String> skills) {

        HttpStatus status;
        //Categories category = new Categories();
        //category.setId(categoryId);

        Categories category = categoriesRepository.findById(categoryId).get();

        //ProjectStatus projectStatus = new ProjectStatus();
        //projectStatus.setId(1L);

        ProjectStatus projectStatus = projectStatusRepository.findById(1L).get();
        //ProjectRoles projectRoles = projectRoleRepository.findById(1L).get();

        //ProjectRoles projectRole = new ProjectRoles();

        /*Users user = new Users();
        user.setId(userId);
        List<Users> users = new ArrayList<>();
        users.add(user);*/

        // HOW CAN I SAVE A NEW DISCUSSION BOARD AND CHAT BOARD?
        //DiscussionBoard discussionBoard = discussionBoardRepository.save(new DiscussionBoard());
        //ChatBoard chatBoard = chatBoardRepository.save(new ChatBoard());

        // HOW CAN I SAVE PROJECT ROLES?

        Projects project = new Projects();
        project.setTitle(title);
        project.setDescription(description);
        project.setCreatedDate(createdDate);
        project.setCategory(category);
        project.setProjectStatus(projectStatus);
        //project.setUsers(users);
        //project.setDiscussionBoard(discussionBoard);
        //project.setChatBoard(chatBoard);

        //System.out.println("ProjectID: " + project.getId());

        //System.out.println(skills);
        ArrayList<Skills> skillsList = new ArrayList<>();
        for (String skill: skills) {
            Skills newSkill = skillsRepository.findById(Long.parseLong(skill)).get();
            //Skills newSkill = new Skills();
            //newSkill.setId(Long.parseLong(skill));
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

    /**
     * Update existing project
     *
     * @param id - the project id
     * @param title - title
     * @param description
     * @param projectStatusId
     * @return the updated project.
     */
    public ResponseEntity<Projects> updateProject(Long id, String title, String description, Long projectStatusId) {

        HttpStatus status;

        Projects project;// = new Projects();
        project = projectsRepository.getById(id);
        project.setTitle(title);
        project.setDescription(description);
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setId(projectStatusId);
        project.setProjectStatus(projectStatus);
        status = HttpStatus.CREATED;

        project = projectsRepository.save(project);
        return new ResponseEntity<>(project, status);
    }

    /**
     * Deletes project from the database
     *
     * @param id - project id
     * @return - the database without the deleted project.
     */
    public ResponseEntity<HttpStatus> deleteProject(Long id) {
        try {
            projectsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
