package gallery;

import java.awt.Dimension;

/**
 * JPanel to display blank image panel at start.
 *
 * @author Aditee Nagle
 */
public class BlankImagePanel extends ImagePanel {

    ImageInfo imgInfo;

    public BlankImagePanel() {
        imgInfo = null;
        setPreferredSize(new Dimension(100, 200));
        setOpaque(true);
        setVisible(true);
    }

    public void refresh() {
        repaint();
    }

    public ImageInfo getImageInfo() {
        return imgInfo;
    }

    public void setImageInfo(ImageInfo info) {
        this.imgInfo = info;
    }
}
