package gallery;

import javax.swing.ImageIcon;

/**
 * Class representing object that holds information regarding a specific image.
 *
 * @author Aditee Nagle
 */
public class ImageInfo {

    /**
     * username of image owner.
     */
    private String ownerName;
    /**
     * Image name.
     */
    private String imageName;
    /**
     * Image icon .
     */
    private ImageIcon image;
    /**
     * Full path of image on local machine.
     */
    private String imagePath;
    /**
     * grade assigned to this image artifact.
     */
    private double grade;
    /**
     * Flag to show if image is framed.
     */
    private boolean isFramed;
    /**
     * Flag to show if image is matted.
     */
    private boolean isMatted;
    /**
     * Caption for this image.
     */
    private String caption;

    public ImageInfo() {
        this.ownerName = null;
        this.imageName = null;
        this.image = null;
        imagePath = null;
        this.grade = 0.0;
        this.isFramed = false;
        this.isMatted = false;
        this.caption = null;
    }

    public void setOwnerName(String name) {
        this.ownerName = name;
    }

    public String getOwerName() {
        return this.ownerName;
    }

    public void setImageName(String name) {
        this.imageName = name;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageIcon(ImageIcon icon) {
        this.image = icon;
    }

    public ImageIcon getImageIcon() {
        return this.image;
    }

    public void setImagePath(String path) {
        this.imagePath = path;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double getGrade() {
        return this.grade;
    }

    public void setIsFramed(boolean value) {
        this.isFramed = value;
    }

    public boolean getIsFramed() {
        return this.isFramed;
    }

    public void setIsMatted(boolean value) {
        this.isMatted = value;
    }

    public boolean getIsMatted() {
        return this.isMatted;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return this.caption;
    }

}
