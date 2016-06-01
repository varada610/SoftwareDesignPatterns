package loginPackage;

/**
 * Defines interface for strategies used for authentication.
 *
 * @author Aditee Nagle
 */
public interface AuthenticationStrategy {

    /**
     * Method to authenticate user.
     *
     * @param username - username provided by user
     * @param password - password provided by user
     * @return - true if passwords match, false otherwise
     */
    public boolean authenticate(String username, String password);
}
