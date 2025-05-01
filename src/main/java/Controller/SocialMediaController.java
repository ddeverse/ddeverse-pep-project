package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import static org.mockito.ArgumentMatchers.nullable;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController()
    {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::userRegisterHandler);
        app.post("/login", this::userLoginHandler);
        app.post("/messages", this::newMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessgeByIdHandler);


        return app;
    }

    /**
     * As a user, I should be able to create a new Account on the endpoint POST localhost:8080/register. 
     * The body will contain a representation of a JSON Account, but will not contain an account_id.
     * The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, and an Account with that username does not already exist. 
     * If all these conditions are met, the response body should contain a JSON of the Account, including its account_id. 
     * The response status should be 200 OK, which is the default. 
     * The new account should be persisted to the database. If the registration is not successful, the response status should be 400. (Client error)
     *
     * @return 200 OK if successful, 400 (Client error) if not
     */
    private void userRegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.createAccount(account);
        if(addedAccount!=null){
            ctx.json(mapper.writeValueAsString(addedAccount));
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }

    /**
     * As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. 
     * The request body will contain a JSON representation of an Account, not containing an account_id. 
     * In the future, this action may generate a Session token to allow the user to securely use the site. We will not worry about this for now. 
     * The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. 
     * If successful, the response body should contain a JSON of the account in the response body, including its account_id. 
     * The response status should be 200 OK, which is the default. If the login is not successful, the response status should be 401. (Unauthorized)
     *
     * @return 200 OK if successful, 401 (Unauthorized) if not
     */
    private void userLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account loggedIn = accountService.newLogin(account);
        if(loggedIn!=null){
            ctx.json(mapper.writeValueAsString(loggedIn));
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    }

    /**
     * As a user, I should be able to submit a new post on the endpoint POST localhost:8080/messages. 
     * The request body will contain a JSON representation of a message, which should be persisted to the database, but will not contain a message_id.
     * The creation of the message will be successful if and only if the message_text is not blank, is under 255 characters, and posted_by refers to a real, existing user. 
     * If successful, the response body should contain a JSON of the message, including its message_id. The response status should be 200, which is the default. The new message should be persisted to the database.
     * If the creation of the message is not successful, the response status should be 400. (Client error)
     *
     * @return 200 OK if successful, 400 (Client Error) if not
     */
    private void newMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message newMessage = messageService.newMessage(message);
        if(newMessage!=null){
            ctx.json(mapper.writeValueAsString(newMessage));
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }

    /**
     * Handler to retrieve all messages.
     */
    private void getAllMessagesHandler(Context ctx) throws JsonProcessingException {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    /**
     * As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages/{message_id}.
     * The response body should contain a JSON representation of the message identified by the message_id. 
     * It is expected for the response body to simply be empty if there is no such message. 
     * The response status should always be 200, which is the default.
     *
     * @return 200 by default
     */
    private void getMessgeByIdHandler(Context ctx) throws JsonProcessingException {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(messageId);

        if(message != null){
            ctx.json(message);
        } else {
            ctx.result("");
        }
    }

    /**
     * As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. 
     * The request body will contain a JSON representation of an Account, not containing an account_id. 
     * In the future, this action may generate a Session token to allow the user to securely use the site. We will not worry about this for now. 
     * The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. 
     * If successful, the response body should contain a JSON of the account in the response body, including its account_id. 
     * The response status should be 200 OK, which is the default. If the login is not successful, the response status should be 401. (Unauthorized)
     *
     * @return 200 OK if successful, 401 (Unauthorized) if not
     */
    private void deleteMessageHandler(Context ctx) throws JsonProcessingException {
        
    }

    /**
     * As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. 
     * The request body will contain a JSON representation of an Account, not containing an account_id. 
     * In the future, this action may generate a Session token to allow the user to securely use the site. We will not worry about this for now. 
     * The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. 
     * If successful, the response body should contain a JSON of the account in the response body, including its account_id. 
     * The response status should be 200 OK, which is the default. If the login is not successful, the response status should be 401. (Unauthorized)
     *
     * @return 200 OK if successful, 401 (Unauthorized) if not
     */
    private void updateMessageHandler(Context ctx) throws JsonProcessingException {
        
    }

    /**
     * As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. 
     * The request body will contain a JSON representation of an Account, not containing an account_id. 
     * In the future, this action may generate a Session token to allow the user to securely use the site. We will not worry about this for now. 
     * The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. 
     * If successful, the response body should contain a JSON of the account in the response body, including its account_id. 
     * The response status should be 200 OK, which is the default. If the login is not successful, the response status should be 401. (Unauthorized)
     *
     * @return 200 OK if successful, 401 (Unauthorized) if not
     */
    private void getIdMessagesHandler(Context ctx) throws JsonProcessingException {
        
    }

}