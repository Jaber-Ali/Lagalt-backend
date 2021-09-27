package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.PortfolioItem;
import com.example.lagaltbackend.models.Skills;
import com.example.lagaltbackend.models.Users;
import com.example.lagaltbackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "https://lagalt-frontend-gbg.herokuapp.com/",
        "http://localhost:3000/",
        "http://localhost:3000/profile",
        "https://lagalt-chat.herokuapp.com/"})

@RequestMapping("/api/v1/users")

public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * Get all users from database.
     *
     * @return - All users.
     */
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return usersService.getAllUsers();
    }
    //TODO fix path

    /**
     * gets the user with the selected id
     *
     * @param id the Id of the user you want to fetch
     * @return the fetched user
     */
    @GetMapping("/i/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Long id) {
        return usersService.getUserById(id);
    }

    /**
     * get the id of the user
     *
     * @param email the email of the user you want to fetch id from
     * @return id of the user
     */
    @GetMapping("/{email}")
    public ResponseEntity<Long> getUserId(@PathVariable("email") String email) {
        return usersService.getUser(email);
    }

    /**
     * fetches userPortfolio from the user
     *
     * @param id the id of the user you want to fetch userPortfolio from
     * @return userPortfolio of the user
     */
    @GetMapping("/portfolio/{id}")
    public ResponseEntity<List<PortfolioItem>> getUserPortfolio(@PathVariable Long id) {
        return usersService.getUserPortfolio(id);
    }

    /**
     * fetches Skills from the user
     *
     * @param id the id of the user you want to fetch Skills from
     * @return Skills of the user
     */
    @GetMapping("/skills/{id}")
    public ResponseEntity<List<Skills>> getUserSkills(@PathVariable Long id) {
        return usersService.getUserSkills(id);
    }

    /**
     * adds skills to the specific user
     *
     * @param userId  the id of the User
     * @param skillId the id of the SKill you want to add to the user
     * @return The added skill
     */
    @PostMapping("/{userId}/skills/{skillId}")
    public ResponseEntity addUserSkill(@PathVariable Long userId, @PathVariable Long skillId) {
        return usersService.addUserSkill(userId, skillId);
    }

    /**
     * Deletes a skill for a User
     *
     * @param userId  the id of the User
     * @param skillId the id of the SKill you want to remove to the user
     * @return list of skill without the removed skill
     */
    @DeleteMapping("/{userId}/skills/{skillId}")
    public ResponseEntity removeUserSkill(@PathVariable Long userId, @PathVariable Long skillId) {
        return usersService.removeUserSkill(userId, skillId);
    }

    /**
     * Adds a User to the DB
     *
     * @param user The user you want to add to the DB
     * @return The added User
     */
    @PostMapping
    public ResponseEntity<Users> addUserToDB(@RequestBody Users user) {
        Users newUser = usersService.addUser(user);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity(newUser, status);
    }

}
