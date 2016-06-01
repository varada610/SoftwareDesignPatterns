package gallery;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * Class that decorates image with frame border.
 *
 * @author Aditee Nagle
 */
public class ImagePanelFrameDecorator extends ImagePanelDecorator {

    public ImagePanelFrameDecorator(ImagePanel base) {
        this.enclosedPanel = base;
        setContents();
        setVisible(true);
    }

    private void setContents() {
        ImageInfo imgInfo = enclosedPanel.getImageInfo();

        String framed = "Framed";

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Color frameColor = new Color(102, 0, 0);
        Border frameBorder = BorderFactory.createLineBorder(frameColor, 15, false);

        enclosedPanel.setBorder(frameBorder);
        add(enclosedPanel);

        JLabel framedLabel = new JLabel(framed);
        framedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(framedLabel);
    }

    public void refresh() {
        removeAll();
        setContents();
        enclosedPanel.refresh();
        repaint();
        setVisible(true);
    }

}
