package gallery;

import databaseAccessPackage.GalleryAccess;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * Panel for showing image details of selected image. Observes gallery database
 * to update itself whenever change is made.
 *
 * @author Aditee Nagle
 */
public class ImageDetailsPanel extends JPanel implements Observer {

    GalleryAccess galleryDBA;
    /**
     * Owner of the image being displayed.
     */
    String userName;
    /**
     * Name of image being displayed.
     */
    String imageName;

    public ImageDetailsPanel(String userName, String imageName, GalleryAccess dba) {
        this.userName = userName;
        this.imageName = imageName;
        this.galleryDBA = dba;
        galleryDBA.addObserver(this);

        addPanelContents();
        setVisible(true);
    }

    private void addPanelContents() {
        if ((userName != null) && (imageName != null)) {
            ImageInfo imgInfo = galleryDBA.getImageDetails(userName, imageName);
            //Create image panel
            ImagePanel imagePanel = null;
            if (imgInfo != null) {
                ImagePanel basePanel = new PhotoImagePanel(imgInfo);
                if (imgInfo.getIsMatted()) {
                    if (imgInfo.getCaption() != null) {
                        imagePanel = new ImagePanelCaptionDecorator(new ImagePanelMattDecorator(basePanel));
                    } else {
                        imagePanel = new ImagePanelMattDecorator(basePanel);
                        System.out.println("Created matted panel");
                    }
                } else if (imgInfo.getIsFramed()) {
                    if (imgInfo.getCaption() != null) {
                        imagePanel = new ImagePanelCaptionDecorator(new ImagePanelFrameDecorator(basePanel));
                    } else {
                        imagePanel = new ImagePanelFrameDecorator(basePanel);
                    }
                } else {
                    imagePanel = basePanel;
                }
            }

            String gradeString;
            if (imgInfo.getGrade() == 0.0) {
                gradeString = "Grade not yet assigned";
            } else {
                gradeString = "Grade: " + imgInfo.getGrade();
            }
            JLabel gradeLabel = new JLabel(gradeString);
            gradeLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            gradeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(imagePanel);
            add(gradeLabel);
            Border raisedbevel = BorderFactory.createRaisedBevelBorder();
            Border margin = new EmptyBorder(10, 10, 10, 10);
            this.setBorder(BorderFactory.createCompoundBorder(raisedbevel, margin));
        }
    }

    public void showSelectedImage(String userName, String imageName) {
        this.userName = userName;
        this.imageName = imageName;
        removeAll();
        addPanelContents();
        validate();
        setVisible(true);
    }

    public void update(Observable observable, Object object) {
        showSelectedImage(this.userName, this.imageName);
    }

    public String getCurrentImageUserName() {
        return this.userName;
    }

    public String getCurrentImageName() {
        return this.imageName;
    }
}
