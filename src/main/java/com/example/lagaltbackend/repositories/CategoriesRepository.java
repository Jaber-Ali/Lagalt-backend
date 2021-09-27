package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
