package gallery;

import databaseAccessPackage.GalleryAccess;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import loginPackage.Session;

/**
 * Panel to show clickable image icons.
 * Observes gallery database to update itself if changes are made.
 *
 * @author Aditee Nagle
 */
public class ImageIconPanel extends JPanel implements Observer {

    GalleryAccess galleryDBA;
    /**
     * List of images to be displayed as icons in this panel.
     */
    List<String> imageList;
    /**
     * Current session
     */
    Session session;
    MouseListener listener;

    public ImageIconPanel(Session session, MouseListener listener, GalleryAccess dba) {
        this.setLayout(new GridLayout(5, 5));
        this.session = session;
        this.listener = listener;
        this.galleryDBA = dba;
        galleryDBA.addObserver(this);
        setPanelContent();
        setVisible(true);
    }

    private void setPanelContent() {
        if (session.getAccountType().equalsIgnoreCase("teacher")) {
            setPanelContentTeacher();
        } else {
            setPanelContentStudent();
        }

    }

    private void setPanelContentStudent() {
        imageList = galleryDBA.getListOfImages(session.getUserName());
        if (imageList != null) {
            for (String imageName : imageList) {
                ImageIcon icon = galleryDBA.getImageIcon(session.getUserName(), imageName);
                Image smallImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                ImageJLabel label = new ImageJLabel(session.getUserName(), imageName, smallIcon, JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.BOTTOM);
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.addMouseListener(listener);
                this.add(label);
            }
        }

    }

    private void setPanelContentTeacher() {
        List<String> imageOwnersList = galleryDBA.getAllImageOwners();
        if (imageOwnersList != null) {
            for (String owner : imageOwnersList) {
                List<String> imageList = galleryDBA.getListOfImages(owner);
                for (String imageName : imageList) {
                    ImageIcon icon = galleryDBA.getImageIcon(owner, imageName);
                    Image smallImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    ImageIcon smallIcon = new ImageIcon(smallImage);
                    ImageJLabel label = new ImageJLabel(owner, imageName, smallIcon, JLabel.CENTER);
                    label.setVerticalTextPosition(JLabel.BOTTOM);
                    label.setHorizontalTextPosition(JLabel.CENTER);
                    label.addMouseListener(listener);
                    this.add(label);
                }
            }
        }

    }

    public void update(Observable observable, Object object) {
        removeAll();
        setPanelContent();
        validate();
        setVisible(true);
    }
}
