package loginPackage;

/**
 * Class responsible for authenticating users.
 *
 * @author Aditee Nagle
 */
public class Authenticator {

    /**
     * Strategy to be used for authentication.
     */
    AuthenticationStrategy strategy;

    /**
     * Constructor.
     */
    public Authenticator() {
        this.strategy = new SimpleAuthenticationStrategy();
    }

    /**
     * Method to authenticate user.
     *
     * @param user - username provided by user
     * @param password - password provided by user
     * @return - true if authenticated, false otherwise
     */
    public boolean authenticate(String user, String password) {
        if (strategy.authenticate(user, password)) {
            System.out.println("Login Success!");
            return true;
        } else {
            System.out.println("Login Failed!");
            return false;
        }
    }

}
