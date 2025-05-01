package Service;

import DAO.AccountDAO;
import Model.Account;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    
    private AccountDAO accountDAO;

    public AccountService()
    {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /**
     * TODO: Use the AccountDAO to retrieve all accounts.
     *
     * @return all authors
     */
    public List<Account> getAllAccounts() { 
        return accountDAO.getAllAccounts();
    }

    /**
     * TODO: Use AccountDAO to create a unique account, with a minimum of 4 characters
     * 
     * @param Account the name of the new account
     * @return the new account if successfull
     */
    public Account createAccount(Account account)
    {
        boolean notEmpty = (account.getUsername() != null && !account.getUsername().trim().isEmpty());
        boolean goodPassword = (account.getPassword() != null && account.getPassword().length() >= 4);
        boolean notDupe = true;

        List<Account> accounts = getAllAccounts();
        for(int i = 0; i < accounts.size(); i++)
        {
            if(account.getUsername().equals(accounts.get(i).getUsername()))
            {
                notDupe = false;
                break;
            }
        }

        if(notEmpty & goodPassword & notDupe)
        {
            return accountDAO.insertAccount(account);
        }
        else{
            return null;
        }
    }

    /**
     * TODO: Use AccountDAO to completed a login
     * 
     * @param Account account attempting to login
     * @return Account account that was logged into
     */
    public Account newLogin(Account account)
    {
        return accountDAO.getAccountByLogin(account);
    }

}
