package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}

