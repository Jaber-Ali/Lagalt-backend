package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.PortfolioItem;
import com.example.lagaltbackend.models.Skills;
import com.example.lagaltbackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Fetches a user based on keycloak email
     * @param keycloakEmail the keycloak email that you want to use to search the user
     * @return the selected User
     */
    List<Users> findByKeycloakEmail(String keycloakEmail);

    /**
     * Gets the id of the user based on email
     * @param email the email used to find the userId
     * @return The id of the user
     */
    @Query ("SELECT id FROM Users WHERE keycloakEmail = ?1")
    Long getUserId(String email);

    /**
     * Gets the PortfolioItems of the user based on id
     * @param id the id used to find the PortfolioItems
     * @return list of PortfolioItems
     */
    @Query("SELECT p FROM PortfolioItem p WHERE p.user.id = ?1")
    List<PortfolioItem> getUserPortfolioById(Long id);

    /**
     * Gets the Skills of the user based on id
     * @param id the id used to find the Skills
     * @return list of Skills
     */
    @Query("SELECT up.skills from UserProfile up WHERE up.id = ?1 ")
    List<Skills> getUserSkillsById(Long id);
}
