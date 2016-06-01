package databaseAccessPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Observable;

import loginPackage.Session;

/**
 * Class used for accessing underlying database relation of user accounts.
 * Provides interface for performing database related activities. Currently uses
 * MySQL database.
 *
 * @author Aditee Nagle
 */
public class AccountAccess extends Observable {

    /**
     * username for database access
     */
    private String DBUser;
    /**
     * password for database access
     */
    private String DBpassword;
    /**
     * url for database access
     */
    private String url;

    /**
     * Constructor no-arg.
     */
    public AccountAccess() {
        this("root", "wireless");
    }

    /**
     * Constructor
     *
     * @param user - specified user for accessing database
     * @param password - password for user
     */
    public AccountAccess(String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found. " + e);
        }
        this.DBUser = user;
        this.DBpassword = password;
        this.url = "jdbc:mysql://localhost:3306/artkit";
    }

    /**
     * Method to get account login password from database.
     *
     * @param userID - username for which password is to be retrieved
     * @return - password
     */
    public String getPassword(String userID) {
        Connection con = null;
        String password = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            //System.out.println("Got connections");
            stmt = con.prepareStatement("SELECT password FROM accounts WHERE username = ?");
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()) {
                rs.next();
                password = rs.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing connection. " + e);
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing statement. " + e);
                }
            }
        }

        return password;
    }

    /**
     * Method to obtain account information to create a session.
     *
     * @param userID - username for which session is to be created
     * @param session - session object containing account information
     */
    public void setSessionProperties(String userID, Session session) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.prepareStatement("SELECT * FROM accounts WHERE username = ?");
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            session.setUserName(rs.getString("username"));
            session.setAccountType(rs.getString("accountType"));
            session.setAccountBalance(rs.getInt("balance"));
            session.setPrevLoginTime(rs.getTimestamp("prevLoginTime"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing connection. " + e);
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing statement. " + e);
                }
            }
        }
    }

    /**
     * Method to update previous login time stamp.
     *
     * @param userID - username for which time stamp is to be updated
     * @param newTime - new time stamp
     */
    public void updatePrevLogin(String userID, Timestamp newTime) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.prepareStatement("UPDATE accounts SET prevLoginTime = ? WHERE username = ?");
            stmt.setTimestamp(1, newTime);
            stmt.setString(2, userID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing connection. " + e);
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing statement. " + e);
                }
            }
        }
    }

    /**
     * Method to retrieve account balance of a user.
     *
     * @param userID - username for which balance is to be retrieved
     * @return - balance
     */
    public double getAccountBalance(String userID) {
        Connection con = null;
        PreparedStatement stmt = null;
        double balance = 0;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.prepareStatement("SELECT balance FROM accounts WHERE username = ?");
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            balance = rs.getDouble("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing connection. " + e);
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing statement. " + e);
                }
            }
        }

        return balance;
    }

    /**
     * Method to update account balance.
     *
     * @param userID - username for which balance is to be updated
     * @param balance - balance value to be set
     */
    public void setAccountBalance(String userID, double balance) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.prepareStatement("UPDATE accounts SET balance = ? WHERE username = ?");
            stmt.setDouble(1, balance);
            stmt.setString(2, userID);
            int success = stmt.executeUpdate();
            if (success > 0) {
                setChanged();
                notifyObservers();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing connection. " + e);
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Problem closing statement. " + e);
                }
            }
        }

    }
}
