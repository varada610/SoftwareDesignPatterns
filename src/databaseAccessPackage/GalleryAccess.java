package databaseAccessPackage;

import gallery.ImageInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.ImageIcon;

/**
 * Class used for accessing underlying database relation of user gallery.
 * Provides interface for performing database related activities. Currently uses
 * MySQL database.
 *
 * @author Aditee Nagle
 */
public class GalleryAccess extends Observable {

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
    public GalleryAccess() {
        this("root", "wireless");
    }

    /**
     * Constructor
     *
     * @param user - specified user for accessing database
     * @param password - password for user
     */
    public GalleryAccess(String user, String password) {
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
     * Method to obtain list of image names for specified user.
     *
     * @param userID - username for which list is to be retrieved.
     * @return - list of image names.
     */
    public List<String> getListOfImages(String userID) {
        List<String> imageList = new ArrayList<String>();
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.prepareStatement("SELECT imagename FROM imagegallery WHERE username = ?");
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                imageList.add(rs.getString("imagename"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException in getListOfImages");
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

        return imageList;
    }

    /**
     * Method to retrieve user names who have saved at least one image in
     * database.
     *
     * @return - list of usernames who are image owners
     */
    public List<String> getAllImageOwners() {
        List<String> imageOwnersList = new ArrayList<String>();
        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT distinct username FROM imagegallery");
            while (rs.next()) {
                imageOwnersList.add(rs.getString("username"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException in getListOfImages");
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

        return imageOwnersList;
    }

    /**
     * Method to retrieve image.
     *
     * @param userID - username of image owner.
     * @param imagename - image name of image to be retrieved.
     * @return - image
     */
    public ImageIcon getImageIcon(String userID, String imagename) {
        ImageIcon icon = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.prepareStatement("SELECT image FROM imagegallery WHERE username = ? and imagename = ?");
            stmt.setString(1, userID);
            stmt.setString(2, imagename);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Blob blob = rs.getBlob("image");
            int blobLength = (int) blob.length();
            byte[] bytes = blob.getBytes(1, blobLength);
            blob.free();
            icon = new ImageIcon(bytes);
        } catch (SQLException e) {
            System.out.println("SQLException in getImageIcon");
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
        return icon;
    }

    /**
     * Method to retrieve image with its details
     *
     * @param userID - username of image owner.
     * @param imagename - image name of image to be retrieved.
     * @return - ImageInfo object with image details.
     */
    public ImageInfo getImageDetails(String userID, String imagename) {
        ImageInfo details = new ImageInfo();
        ImageIcon icon = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.prepareStatement("SELECT * FROM imagegallery WHERE username = ? and imagename = ?");
            stmt.setString(1, userID);
            stmt.setString(2, imagename);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            details.setOwnerName(rs.getString("username"));
            details.setImageName(rs.getString("imagename"));
            details.setGrade(rs.getDouble("grade"));
            details.setIsFramed(rs.getBoolean("frame"));
            details.setIsMatted(rs.getBoolean("matt"));
            details.setCaption(rs.getString("caption"));
            Blob blob = rs.getBlob("image");
            int blobLength = (int) blob.length();
            byte[] bytes = blob.getBytes(1, blobLength);
            blob.free();
            icon = new ImageIcon(bytes);
            details.setImageIcon(icon);
        } catch (SQLException e) {
            System.out.println("SQLException in getImageDetails");
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

        return details;
    }

    /**
     * Method to save newly uploaded image to database.
     *
     * @param newImageInfo - ImageInfo object with details of new image.
     */
    public void saveNewImage(ImageInfo newImageInfo) {
        Connection con = null;
        PreparedStatement stmt = null;
        FileInputStream finp = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.prepareStatement("INSERT INTO imagegallery"
                    + "(username, imagename, image, frame, matt, caption)"
                    + " VALUES(?,?,?,?,?,? )");
            stmt.setString(1, newImageInfo.getOwerName());
            stmt.setString(2, newImageInfo.getImageName());
            File file = new File(newImageInfo.getImagePath());
            finp = new FileInputStream(file);
            stmt.setBinaryStream(3, finp, (int) file.length());
            stmt.setBoolean(4, newImageInfo.getIsFramed());
            stmt.setBoolean(5, newImageInfo.getIsMatted());
            System.out.println("Caption for this artifact: " + newImageInfo.getCaption());
            if (newImageInfo.getCaption() != null) {
                stmt.setString(6, newImageInfo.getCaption());
            } else {
                stmt.setString(6, null);
            }
            int success = stmt.executeUpdate();
            if (success > 0) {
                setChanged();
                notifyObservers();
            }
        } catch (SQLException e) {
            System.out.println("SQLException in saveNewImage");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundExcception in saveNewImage");
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
     * Method to update grade assigned to an image.
     *
     * @param userID - username of image owner.
     * @param imageName - image name of image
     * @param grade - new grade assigned
     */
    public void saveGrade(String userID, String imageName, double grade) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(url, DBUser, DBpassword);
            stmt = con.prepareStatement("UPDATE imagegallery SET grade = ? WHERE username = ? AND imagename = ?");
            stmt.setDouble(1, grade);
            stmt.setString(2, userID);
            stmt.setString(3, imageName);
            int success = stmt.executeUpdate();
            if (success > 0) {
                setChanged();
                notifyObservers();
            }
        } catch (SQLException e) {
            System.out.println("SQLException in saveNewImage");
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
