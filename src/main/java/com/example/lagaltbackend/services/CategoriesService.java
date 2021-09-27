package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.Categories;
import com.example.lagaltbackend.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    /**
     * Get all categories
     *
     * @return - all categories
     */
    public ResponseEntity<List<Categories>> getAllCategories() {
        List<Categories> data = categoriesRepository.findAll();

        System.out.println(data);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }

    /**
     * Add Categories to the database
     *
     * @param categories - Categories
     * @return - the Categories
     */
    public ResponseEntity<Categories> addCategories(Categories categories) {
        Categories add = categoriesRepository.save(categories);
        HttpStatus status;
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(add, status);
    }


    /**
     * Get specific Categories
     *
     * @param id -Categories id
     * @return - info about that specific Categories
     */
    public ResponseEntity<Categories> getSpecificCategories( Long id) {
        HttpStatus status;
        Categories add = new Categories();
        if (!categoriesRepository.existsById(id)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(add, status);
        }
        add = categoriesRepository.findById(id).get();
        status = HttpStatus.OK;
        return new ResponseEntity<>(add, status);
    }


    /**
     * Update Categories
     *
     * @param id        - Categories id
     * @param categories - the new Categories
     * @return - newly updated Categories.
     */
    public ResponseEntity<Categories> updateCategories( Long id, Categories categories) {
        Optional<Categories> categoriesData = categoriesRepository.findById(id);
        if (categoriesData.isPresent()) {
            Categories category = categoriesData.get();
            category.setTitle(categories.getTitle());


            return new ResponseEntity<>(categoriesRepository.save(category), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Delete Categories
     *
     * @param id - Categories-id to be deleted
     * @return - Categories without the deleted Categories.
     */
    public ResponseEntity<HttpStatus> deleteCategories(Long id) {
        try {
            categoriesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
