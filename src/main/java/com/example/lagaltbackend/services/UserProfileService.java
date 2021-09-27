package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.UserProfile;
import com.example.lagaltbackend.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     * Get all users from database.
     *
     * @return - All users.
     */
    public ResponseEntity<UserProfile> getUserProfileById(Long id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        return userProfile.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates the about-text stored in the db with a new one.
     * @param userId Id of the user to update.
     * @param about The new about-text.
     * @return - user
     */
    public ResponseEntity<String> postUserAbout(Long userId, String about) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        UserProfile user = userProfileRepository.findById(userId).get();
        try {
        user.setAbout(about);

        userProfileRepository.save(user);
        status = HttpStatus.OK;
        }catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity(user, status );
        }
}

