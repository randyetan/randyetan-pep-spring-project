package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

/*
 * "have a bean for the AccountService, MessageService, AccountRepository, MessageRepository, and SocialMediaController classes"
 * 
 * User Registration
As a user, I should be able to create a new Account on the endpoint POST localhost:8080/register. The body will contain a representation of a JSON Account, but will not contain an account_id.

The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, and an Account with that username does not already exist. If all these conditions are met, the response body should contain a JSON of the Account, including its account_id. The response status should be 200 OK, which is the default. The new account should be persisted to the database.
If the registration is not successful due to a duplicate username, the response status should be 409. (Conflict)
If the registration is not successful for some other reason, the response status should be 400. (Client error)
 
*
Login
As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. The request body will contain a JSON representation of an Account, not containing an account_id. In the future, this action may generate a Session token to allow the user to securely use the site. We will not worry about this for now.

The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. If successful, the response body should contain a JSON of the account in the response body, including its account_id. The response status should be 200 OK, which is the default.

If the login is not successful, the response status should be 401. (Unauthorized)
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findById(id);
    }
}
