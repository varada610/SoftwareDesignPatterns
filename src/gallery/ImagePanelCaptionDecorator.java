package gallery;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 * Class that decorates image with caption label.
 *
 * @author Aditee Nagle
 */
public class ImagePanelCaptionDecorator extends ImagePanelDecorator {

    public ImagePanelCaptionDecorator(ImagePanel base) {
        this.enclosedPanel = base;
        setContents();
        setVisible(true);
    }

    private void setContents() {
        ImageInfo imgInfo = enclosedPanel.getImageInfo();
        String caption;
        if (imgInfo.getCaption() != null) {
            caption = imgInfo.getCaption();
        } else {
            caption = "No caption provided";
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(enclosedPanel);

        JLabel imageCaption = new JLabel(caption);
        imageCaption.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(imageCaption);

    }

    public void refresh() {
        removeAll();
        setContents();
        enclosedPanel.refresh();
        repaint();
        setVisible(true);
    }
}
