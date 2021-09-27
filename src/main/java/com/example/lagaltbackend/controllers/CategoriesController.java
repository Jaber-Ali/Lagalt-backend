package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.Categories;
import com.example.lagaltbackend.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/", "http://localhost:3000/"})
@RequestMapping("/api/v1/categories")

public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    /**
     * Get all categories
     *
     * @return - all categories
     */
    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    /**
     * Add Categories to the database
     *
     * @param categories - Categories
     * @return - the Categories
     */
    @PostMapping
    public ResponseEntity<Categories> addCategories(@RequestBody Categories categories) {
        return categoriesService.addCategories(categories);
    }

    /**
     * Get specific Categories
     *
     * @param id -Categories id
     * @return - info about that specific Categories
     */
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getSpecificCategories(@PathVariable Long id) {
        return categoriesService.getSpecificCategories(id);
    }

    /**
     * Update Categories
     *
     * @param id        - Categories id
     * @param categories - the new Categories
     * @return - newly updated Categories.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategories(@PathVariable("id") Long id, @RequestBody Categories categories) {
        return categoriesService.updateCategories(id, categories);
    }

    /**
     * Delete Categories
     *
     * @param id - Categories-id to be deleted
     * @return - Categories without the deleted Categories.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategories(@PathVariable("id") Long id) {
        return categoriesService.deleteCategories(id);
    }
}
