package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {

}
