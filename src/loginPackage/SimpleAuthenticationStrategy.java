package loginPackage;

import databaseAccessPackage.AccountAccess;

/**
 * Simple authentication strategy. Fetches password from database for username
 * provided. Checks if it matches with the password provided by user.
 *
 * @author Aditee Nagle
 */
public class SimpleAuthenticationStrategy implements AuthenticationStrategy {

    public boolean authenticate(String username, String password) {
        AccountAccess dba = new AccountAccess();
        String passwordFromDB = dba.getPassword(username);
        return (password.equals(passwordFromDB));
    }

}
