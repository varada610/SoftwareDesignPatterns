package loginPackage;

import java.sql.Timestamp;

/**
 * Session to store session parameters.
 *
 * @author Aditee Nagle
 */
public class Session {

    /**
     * username of this session's user.
     */
    private String username;
    /**
     * Account type of user.
     */
    private String accountType;
    /**
     * Account balance in database for this user.
     */
    private double accountBalance;
    /**
     * Previous login time-stamp for this user.
     */
    private Timestamp prevLoginTime;

    /**
     * Constructor.
     */
    public Session() {
        this.username = null;
        this.accountType = null;
        this.accountBalance = 0;
        this.prevLoginTime = null;
    }

    public void setUserName(String user) {
        this.username = user;
    }

    public String getUserName() {
        return this.username;
    }

    public void setAccountType(String type) {
        this.accountType = type;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountBalance(double balance) {
        this.accountBalance = balance;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public void setPrevLoginTime(Timestamp time) {
        this.prevLoginTime = time;
    }

    public Timestamp getPrevLoginTime() {
        return this.prevLoginTime;
    }
}
