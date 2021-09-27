package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}
