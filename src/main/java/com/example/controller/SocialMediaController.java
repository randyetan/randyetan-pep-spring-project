package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 * 
 * "have a bean for the AccountService, MessageService, AccountRepository, MessageRepository, and SocialMediaController classes"
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account) {
        
        if(account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if(account.getPassword() == null || account.getPassword().length() < 4) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if(accountService.getAccountByUsername(account.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Account newUser = accountService.createAccount(account);

        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        
        Optional<Account> existingAccount = accountService.getAccountByUsername(account.getUsername());

        if (!existingAccount.isEmpty() && existingAccount.get().getPassword().equals(account.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(existingAccount.get());
            
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Optional<Account>> getAccountById(@PathVariable Integer id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {

        if(message.getMessageText().isEmpty() || message.getMessageText().length() > 255) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if(accountService.getAccountById(message.getPostedBy()).orElse(null) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(messageService.createMessage(message));
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer id) {
        Message deleteMessage = messageService.getMessageById(id);

        if(deleteMessage != null) {
            messageService.deleteMessageById(id);
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByUserId(@PathVariable Integer account_id) {
        List<Message> messages = messageService.getMessagesByUserId(account_id);
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer message_id, @RequestBody Map<String, String> extractString) {
        String message_text = extractString.get("messageText");
        if(message_text == null || message_text.trim().isEmpty() || message_text.length() > 255) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        Message message = messageService.getMessageById(message_id);

        if(message == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        boolean updated = messageService.updateMessage(message_id, message_text);

        if(updated) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        
    }

}
