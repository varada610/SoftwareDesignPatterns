package gallery;

import databaseAccessPackage.GalleryAccess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import loginPackage.Session;

/**
 * JPanel for gallery.
 *
 * @author Aditee Nagle
 */
public class GalleryPanel extends JPanel implements ActionListener, MouseListener {

    /**
     * Panel to show available images as clickable icons.
     */
    ImageIconPanel iconPanel;
    /**
     * Panel to show details of image.
     */
    ImageDetailsPanel detailPanel;
    /**
     * Panel to show UI for uploading new image (student account).
     */
    UploadArtifactPanel uploadPanel;
    /**
     * Panel to show UI for grading images (Teacher account).
     */
    GradeArtifactPanel gradePanel;

    /**
     * Gallery access object.
     */
    GalleryAccess galleryDBA = new GalleryAccess();

    public GalleryPanel(Session session) {
        setLayout(new BorderLayout());

        iconPanel = new ImageIconPanel(session, this, galleryDBA);
        iconPanel.setPreferredSize(new Dimension(200, 200));
        iconPanel.setBorder(new LineBorder(Color.BLACK));

        detailPanel = new ImageDetailsPanel(session.getUserName(), null, galleryDBA);
        detailPanel.setLayout(new BorderLayout());
        detailPanel.setPreferredSize(new Dimension(100, 200));
        detailPanel.setBorder(new LineBorder(Color.BLACK));

        uploadPanel = new UploadArtifactPanel(session, galleryDBA);
        uploadPanel.setBorder(new LineBorder(Color.BLACK));

        gradePanel = new GradeArtifactPanel(this);

        add(iconPanel, BorderLayout.WEST);
        add(detailPanel, BorderLayout.CENTER);
        if (session.getAccountType().equalsIgnoreCase("teacher")) {
            add(gradePanel, BorderLayout.PAGE_END);
        } else {
            add(uploadPanel, BorderLayout.PAGE_END);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        // Action performed for grade button
        if (event.getActionCommand().equalsIgnoreCase("grade")) {
            String userName = detailPanel.getCurrentImageUserName();
            String imageName = detailPanel.getCurrentImageName();
            System.out.println("Grading image = " + imageName + " by " + userName);
            double gradePoint = gradePanel.getEnteredGrade();
            System.out.println("Grade point earned: " + gradePoint);
            galleryDBA.saveGrade(userName, imageName, gradePoint);
            gradePanel.resetPanel();
            iconPanel.validate();
            iconPanel.setVisible(true);
            this.repaint();
        }
    }

    /**
     * Respond to mouseClicked event from iconPanel
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        ImageJLabel label = (ImageJLabel) e.getSource();
        String imageName = label.getText();
        String userName = label.getUserID();
        System.out.println("You clicked on " + imageName + " by " + userName);
        detailPanel.showSelectedImage(userName, imageName);
        detailPanel.validate();
        detailPanel.setVisible(true);
        this.repaint();
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}
