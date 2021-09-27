package com.example.lagaltbackend.repositories;

import com.example.lagaltbackend.models.DiscussionMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionMessagesRepository extends JpaRepository<DiscussionMessages, Long> {
}
