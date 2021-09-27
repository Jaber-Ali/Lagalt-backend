package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.Projects;
import org.hibernate.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {

    /**
     * fetches projects based on some query parameters
     *
     * @param title    the search query
     * @param category the category you want to fetch
     * @return a list of project based the query parameters
     */
    @Query("SELECT p FROM Projects p WHERE (?1 is null or LOWER(p.title) LIKE %?1%) and (?2 is null or ?2 < 0 or p.category.id = ?2)")
    List<Projects> findProjectsList(String title, Long category);

    /**
     * gets a list of recommended project
     *
     * @return a list of projects that are recommended
     */
    @Query(nativeQuery = true, value = "SELECT *  FROM Projects ORDER BY random() LIMIT 4")
    List<Projects> getRecommendedProjects();

}
