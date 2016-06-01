package gallery;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * Class that decorates image with matted frame border.
 *
 * @author Aditee Nagle
 */
public class ImagePanelMattDecorator extends ImagePanelDecorator {

    public ImagePanelMattDecorator(ImagePanel base) {
        this.enclosedPanel = base;
        setContents();
        setVisible(true);
    }

    private void setContents() {

        String framed = "Framed";
        String matted = "Matted";

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Color frameColor = new Color(102, 0, 0);
        Border frameBorder = BorderFactory.createLineBorder(frameColor, 15, false);
        Border mattBorder = BorderFactory.createLineBorder(Color.white, 30, false);
        Border compoundBorder = BorderFactory.createCompoundBorder(frameBorder, mattBorder);
        enclosedPanel.setBorder(compoundBorder);
        add(enclosedPanel);

        JLabel framedLabel = new JLabel(framed);
        framedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(framedLabel);

        JLabel mattedLabel = new JLabel(matted);
        mattedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(mattedLabel);
    }

    public void refresh() {
        removeAll();
        setContents();
        enclosedPanel.refresh();
        repaint();
        setVisible(true);
    }
}
