package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

/*
 * "have a bean for the AccountService, MessageService, AccountRepository, MessageRepository, and SocialMediaController classes"
 * "AccountRepository and MessageRepository are working JPARepositories based on their corresponding Account and Message entities"
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
