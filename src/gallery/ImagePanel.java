package gallery;

import javax.swing.JPanel;

/**
 * Defines interface for Image Panel that shows image icon with appropriate
 * accessories.
 *
 * @author Aditee Nagle
 */
public abstract class ImagePanel extends JPanel {

    /**
     * Method to set contents of panel and repaint itself.
     */
    public abstract void refresh();

    /**
     * Method to return information of image being displayed by this panel.
     *
     * @return - ImageInfo object containing image details
     */
    public abstract ImageInfo getImageInfo();

    /**
     * Method to set information of image to be displayed by this panel.
     *
     * @param info ImageInfo object containing image details
     */
    public abstract void setImageInfo(ImageInfo info);
}
