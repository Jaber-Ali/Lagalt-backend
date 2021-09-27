package com.example.lagaltbackend.controllers;

import com.example.lagaltbackend.models.PortfolioItem;
import com.example.lagaltbackend.services.PortfolioItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://lagalt-frontend-gbg.herokuapp.com/",
        "http://localhost:3000"})
@RequestMapping("/api/v1/portfolio")

public class PortfolioItemController {

    @Autowired
    private PortfolioItemService userPortfolioService;


    /**
     * Get all portfolio items.
     *
     * @return - all portfolio items.
     */
    @GetMapping
    public ResponseEntity<List<PortfolioItem>> getAllUserPortfolioItems() {
        return userPortfolioService.getAllUserPortfolioItems();
    }

    /**
     * Get specific portfolio item based on id.
     *
     * @param portfolioId - Portfolio item id
     * @return - Info about that specific portfolio item.
     */
    @GetMapping("/{portfolioId}")
    public ResponseEntity<PortfolioItem> getSpecificPortfolioItem(@PathVariable Long portfolioId) {
        return userPortfolioService.getSpecificPortfolioItem(portfolioId);
    }

    /**
     * Adds an item to the users portfolio.
     * Takes in startDate and endDate as Strings and parses them to Date-objects.
     *
     * @param company
     * @param title
     * @param startDate
     * @param endDate
     * @param description
     * @param userId
     * @return
     * @throws ParseException
     */
    @PostMapping("/users/{userId}")
    public ResponseEntity<PortfolioItem> addPortfolioItem(@RequestParam("company") String company,
                                                          @RequestParam("title") String title,
                                                          @RequestParam("startDate") String startDate,
                                                          @RequestParam("endDate") String endDate,
                                                          @RequestParam("description") String description,
                                                          @PathVariable Long userId) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedStartDate = format.parse(startDate);
        Date formattedEndDate = format.parse(endDate);
        return userPortfolioService.addPortfolioItem(company, title, formattedStartDate, formattedEndDate, description, userId);
    }

    /**
     * Deletes a PortfolioItem
     * @param portfolioItemId id of PortfolioItem you want to delete
     * @return - the database without the deleted PortfolioItem.
     */
    @DeleteMapping("/{portfolioItemId}")
    public ResponseEntity<PortfolioItem> deletePortfolioItem(@PathVariable Long portfolioItemId) {
        return userPortfolioService.deletePortfolioItem(portfolioItemId);
    }
}

