package gallery;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * New JLabel to save username of image along with the image name and Icon.
 *
 * @author Aditee Nagle
 */
public class ImageJLabel extends JLabel {

    /**
     * username of image owner.
     */
    String userID;

    public ImageJLabel(String userID, String imageName, Icon icon, int horizontalAlignment) {
        super(imageName, icon, horizontalAlignment);
        this.userID = userID;
    }

    public String getUserID() {
        return this.userID;
    }
}
