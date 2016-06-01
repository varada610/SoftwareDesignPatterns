package loginPackage;

import databaseAccessPackage.AccountAccess;
import java.sql.Timestamp;

/**
 * Class responsible for authorizing users.
 *
 * @author Aditee Nagle
 */
public class Authorizer {

    /**
     * Session created for authorized user.
     */
    private Session session;

    /**
     * Constructor.
     */
    public Authorizer() {
        session = new Session();
        System.out.println("Session created");
    }

    /**
     * Method to create session object.
     *
     * @param user - username of authenticated user.
     * @return - session for this user.
     */
    public Session authorize(String user) {
        AccountAccess dba = new AccountAccess();
        dba.setSessionProperties(user, session);

        java.util.Date date = new java.util.Date();
        Timestamp current = new Timestamp(date.getTime());

        dba.updatePrevLogin(user, current);
        return this.session;
    }

}
