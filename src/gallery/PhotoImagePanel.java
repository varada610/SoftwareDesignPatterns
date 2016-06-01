package gallery;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class that displays plain image without any accessories.
 *
 * @author Aditee Nagle
 */
public class PhotoImagePanel extends ImagePanel {

    ImageInfo imageDetails;

    public PhotoImagePanel(ImageInfo details) {
        this.imageDetails = details;
        setContents();
        setVisible(true);
    }

    private void setContents() {

        String description = imageDetails.getImageName() + " by " + imageDetails.getOwerName();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageIcon icon = imageDetails.getImageIcon();
        Image smallImage = icon.getImage().getScaledInstance(450, 300, Image.SCALE_SMOOTH);
        ImageIcon smallIcon = new ImageIcon(smallImage);

        JLabel largeImage = new JLabel(description, smallIcon, JLabel.CENTER);
        largeImage.setVerticalTextPosition(JLabel.BOTTOM);
        largeImage.setHorizontalTextPosition(JLabel.CENTER);
        largeImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(largeImage);

        setPreferredSize(new Dimension(450, 300));
    }

    public void refresh() {
        removeAll();
        setContents();
        repaint();
        setVisible(true);
    }

    public ImageInfo getImageInfo() {
        System.out.println("Returning info: PhotoImageDetailsPanel");
        return this.imageDetails;
    }

    public void setImageInfo(ImageInfo info) {
        this.imageDetails = info;
    }
}
