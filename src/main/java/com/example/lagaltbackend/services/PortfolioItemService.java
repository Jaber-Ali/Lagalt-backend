package com.example.lagaltbackend.services;

import com.example.lagaltbackend.models.PortfolioItem;
import com.example.lagaltbackend.models.Users;
import com.example.lagaltbackend.repositories.PortfolioItemRepository;
import com.example.lagaltbackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
public class PortfolioItemService {

    @Autowired
    private PortfolioItemRepository portfolioItemRepository;
    @Autowired
    private UsersRepository userRepository;

    /**
     * Get all portfolio items.
     *
     * @return - all portfolio items.
     */
    public ResponseEntity<List<PortfolioItem>> getAllUserPortfolioItems() {
        List<PortfolioItem> data = portfolioItemRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }

    /**
     * Get specific portfolio item based on id.
     *
     * @param portfolioId - Portfolio item id
     * @return - Info about that specific portfolio item.
     */
    public ResponseEntity<PortfolioItem> getSpecificPortfolioItem(@PathVariable Long portfolioId) {
        HttpStatus status;
        PortfolioItem portfolioItem = new PortfolioItem();
        if (!portfolioItemRepository.existsById(portfolioId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(portfolioItem, status);
        }
        portfolioItem = portfolioItemRepository.findById(portfolioId).get();
        status = HttpStatus.OK;
        return new ResponseEntity<>(portfolioItem, status);
    }

    /**
     * Add portfolio item to user.
     *
     * @ - The portfolio item to add.
     * @return The portfolio item.
     */
    public ResponseEntity<PortfolioItem> addPortfolioItem(String company, String title, Date startDate, Date endDate, String description, Long userId) {
        HttpStatus status;
        Users user = userRepository.findById(userId).get();
        List<PortfolioItem> list = user.getPortfolioList();
        PortfolioItem item = new PortfolioItem();
        try {
            item.setCompany(company);
            item.setTitle(title);
            item.setStartDate(startDate);
            item.setEndDate(endDate);
            item.setDescription(description);
            item.setUser(user);


            portfolioItemRepository.save(item);
            list.add(item);
            user.setPortfolioList(list);
            userRepository.save(user);
            status = HttpStatus.OK;
        }catch (Exception e) {
            System.out.println(e);
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }
        return new ResponseEntity<>(item , status);
    }

    /**
     * Removes a users portfolio item
     * @param portfolioItemId id of the item to be removed
     * @return
     */
    public ResponseEntity<PortfolioItem> deletePortfolioItem(Long portfolioItemId) {
        HttpStatus status;
        try{
            portfolioItemRepository.deleteById(portfolioItemId);
            status = HttpStatus.OK;
        }catch (Exception e) {
            System.out.println(e);
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }
        return new ResponseEntity<>(status);
    }
}
