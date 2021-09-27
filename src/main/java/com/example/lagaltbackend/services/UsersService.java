package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.PortfolioItem;
import com.example.lagaltbackend.models.Skills;
import com.example.lagaltbackend.models.UserProfile;
import com.example.lagaltbackend.models.Users;
import com.example.lagaltbackend.repositories.SkillsRepository;
import com.example.lagaltbackend.repositories.UserProfileRepository;
import com.example.lagaltbackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:3000/")
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private SkillsRepository skillsRepository;

    /**
     * Get all users from database.
     *
     * @return - All users.
     */
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> data = usersRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }

    /**
     * Adds a new user to the DB.
     * @param user incoming user to be added.
     * @return
     */
    public Users addUser(Users user) {
        try {
            ArrayList<PortfolioItem> portfolio = new ArrayList<>();

            usersRepository.save(user);

            UserProfile profile = new UserProfile();
            profile.setAbout("");
            profile.setHidden(false);
            profile.setPortfolio(portfolio);
            profile.setUser(user);

            user.setUserProfile(profile);

            userProfileRepository.save(profile);
        }catch (Error ignored) {
            //todo; ignore only 500 error
        }
        return user;
    }

    /**
     * Fetches a user based on the email in JWT.
     * @param email email of the user to be fetched.
     * @return
     */
    public ResponseEntity<Long> getUser(String email) {
        Long id = null;
        HttpStatus status;
        try {
            id = usersRepository.getUserId(email);
            status = HttpStatus.OK;
        }catch (Exception e) {
            System.out.println(e);
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Long>(id, status);

    }

    /**
     * Fetches a user based on their id in the DB.
     * @param id id of the user to be fetched.
     * @return
     */
    public ResponseEntity<Users> getUserById(Long id) {
        Optional<Users> users = usersRepository.findById(id);
        return users.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    /**
     * Fetches a users complete portfolio.
     * @param id user id.
     * @return
     */
    public ResponseEntity<List<PortfolioItem>> getUserPortfolio(Long id) {
        List<PortfolioItem> list = new ArrayList<>();
        HttpStatus status;
        try {
            list = usersRepository.getUserPortfolioById(id);
            status = HttpStatus.OK;
        }catch (Exception e) {
            System.out.println(e);
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<PortfolioItem>>(list, status);
    }

    /**
     * Fetches all the skills that a user has saved in their profile.
     * @param id user id.
     * @return
     */
    public ResponseEntity<List<Skills>> getUserSkills(Long id) {
        List<Skills> list = new ArrayList<>();
        HttpStatus status;
        try {
            list = usersRepository.getUserSkillsById(id);
            status = HttpStatus.OK;
        }catch (Exception e) {
            System.out.println(e);
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<Skills>>(list, status);
    }

    /**
     * Adds a new skill to a users profile.
     * @param userId
     * @param skillId
     * @return
     */
    public ResponseEntity<List<Skills>> addUserSkill(Long userId, Long skillId) {
        UserProfile user = userProfileRepository.findById(userId).get();
        List<Skills> skills = user.getSkills();
        Skills skill = skillsRepository.findById(skillId).get();
        HttpStatus status;
        try{
            skills.add(skill);
            user.setSkills(skills);
            userProfileRepository.save(user);
            status = HttpStatus.OK;
        }catch(Exception e) {
            System.out.println("Exception addUserSkill: " + e);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<List<Skills>>(skills, status);
    }

    /**
     * Removes a skill from the users profile.
     * @param userId
     * @param skillId
     * @return
     */
    public ResponseEntity removeUserSkill(Long userId, Long skillId) {
        UserProfile user = userProfileRepository.findById(userId).get();
        List<Skills> skills = user.getSkills();
        Skills skill = skillsRepository.findById(skillId).get();
        HttpStatus status;
        try{
            skills.remove(skill);
            user.setSkills(skills);
            userProfileRepository.save(user);
            status = HttpStatus.OK;
        }catch(Exception e) {
            System.out.println("Exception addUserSkill: " + e);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<List<Skills>>(skills, status);

    }
}
