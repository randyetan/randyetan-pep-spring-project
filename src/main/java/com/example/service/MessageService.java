package com.example.service;
/*
 * "have a bean for the AccountService, MessageService, AccountRepository, MessageRepository, and SocialMediaController classes"
 * Create New Message
As a user, I should be able to submit a new post on the endpoint POST localhost:8080/messages. The request body will contain a JSON representation of a message, which should be persisted to the database, but will not contain a message_id.

The creation of the message will be successful if and only if the message_text is not blank, is under 255 characters, and posted_by refers to a real, existing user. If successful, the response body should contain a JSON of the message, 
including its message_id. The response status should be 200, which is the default. The new message should be persisted to the database.

If the creation of the message is not successful, the response status should be 400. (Client error)

 

* Get All Messages
As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages.

The response body should contain a JSON representation of a list containing all messages retrieved from the database. It is expected for the list to simply be empty if there are no messages. The response status should always be 200, which is the default.

 

* Get One Message Given Message Id
As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages/{message_id}.

The response body should contain a JSON representation of the message identified by the message_id. It is expected for the response body to simply be empty if there is no such message. The response status should always be 200, which is the default.

 

* Delete a Message Given Message Id
As a User, I should be able to submit a DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}.

The deletion of an existing message should remove an existing message from the database. If the message existed, the response body should contain the number of rows updated (1). The response status should be 200, which is the default.
If the message did not exist, the response status should be 200, but the response body should be empty. This is because the DELETE verb is intended to be idempotent, ie, multiple calls to the DELETE endpoint should respond with the same type of response.
 

* Update Message Given Message Id
As a user, I should be able to submit a PATCH request on the endpoint PATCH localhost:8080/messages/{message_id}. The request body should contain a new 
message_text values to replace the message identified by message_id. The request body can not be guaranteed to contain any other information.

The update of a message should be successful if and only if the message id already exists and the new message_text is not blank and is not over 255 characters. 
If the update is successful, the response body should contain the number of rows updated (1), and the response status should be 200, which is the default. The message existing on the database should have the updated message_text.
If the update of the message is not successful for any reason, the response status should be 400. (Client error)
 

* Get All Messages From User Given Account Id
As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/accounts/{account_id}/messages.

The response body should contain a JSON representation of a list containing all messages posted by a particular user, which is retrieved from the database. It is expected for the list to simply be empty if there are no messages. The response status should always be 200, which is the default
 */
public class MessageService {
}
