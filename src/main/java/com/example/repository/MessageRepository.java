package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

/*
 * "have a bean for the AccountService, MessageService, AccountRepository, MessageRepository, and SocialMediaController classes"
 * "AccountRepository and MessageRepository are working JPARepositories based on their corresponding Account and Message entities"
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByPostedBy(Integer postedBy);
}
