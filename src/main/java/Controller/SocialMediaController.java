package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
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
        app.get("/register", this::userRegisterHandler);
        app.post("/register", this::userRegisterHandler);
        app.get("/login", this::userLoginHandler);
        app.post("/login", this::userLoginHandler);

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


}