package com.example.lagaltbackend.controllers;


import com.example.lagaltbackend.models.UserProfile;
import com.example.lagaltbackend.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
        "https://lagalt-frontend-gbg.herokuapp.com/",
        "https://lagalt-frontend-gbg.herokuapp.com/profile",
        "http://localhost:3000/",
        "http://localhost:3000/profile"})

@RequestMapping("/api/v1/userProfile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    /**
     * Gets a UserProfile by id
     *
     * @param id the id of the UserProfile you want to fetch
     * @return the selected UserProfile
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserById(@PathVariable Long id) {
        return userProfileService.getUserProfileById(id);
    }

    /**
     * updates the about for a user
     *
     * @param userId the userId of the user you want to fetch about from
     * @param about  the new about
     * @return the added about
     */
    @PostMapping("/{userId}/about")
    public ResponseEntity<String> postUserAbout(@PathVariable Long userId, @RequestBody String about) {
        return userProfileService.postUserAbout(userId, about);
    }

}
