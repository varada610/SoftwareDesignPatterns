package gallery;

import databaseAccessPackage.GalleryAccess;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import loginPackage.Session;

/**
 * UI Panel to upload new artifact photos. (Used by students)
 *
 * @author Aditee Nagle
 */
public class UploadArtifactPanel extends JPanel {

    String owner;
    GalleryAccess galleryDBA;
    ImageInfo newImageInfo;
    JButton selectFileButton;
    JCheckBox frameButton;
    JCheckBox mattButton;
    JFileChooser fileChooser;
    JTextArea statusArea;
    JTextField captionArea;
    JButton addCaptionButton;
    JButton uploadButton;
    JButton cancelButton;

    public UploadArtifactPanel(Session session, GalleryAccess dba) {
        super(new FlowLayout());

        this.owner = session.getUserName();
        //this.galleryDBA = new GalleryAccess();
        this.galleryDBA = dba;
        newImageInfo = new ImageInfo();
        this.fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        setPanelContents();

        setVisible(true);
    }

    private void setPanelContents() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        //Select file button
        selectFileButton = new JButton("Select File");
        selectFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;
                int returnVal = fileChooser.showOpenDialog(UploadArtifactPanel.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    //This is where a real application would open the file.
                    statusArea.append("File chosen: " + file.getName() + "\n");
                    newImageInfo.setImageName(file.getName());
                    newImageInfo.setOwnerName(owner);
                    newImageInfo.setImagePath(file.getAbsolutePath());
                    System.out.println("File selected: " + file.getName());
                }
                statusArea.setCaretPosition(statusArea.getDocument().getLength());
            }
        });

        statusArea = new JTextArea(20, 4);
        statusArea.setMargin(new Insets(5, 5, 5, 5));
        statusArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(statusArea);

        frameButton = new JCheckBox("Frame It");
        frameButton.setSelected(false);
        frameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (frameButton.isSelected()) {
                    newImageInfo.setIsFramed(true);
                } else {
                    newImageInfo.setIsFramed(false);
                }
                System.out.println("Set isFramed = " + newImageInfo.getIsFramed());
            }
        });

        mattButton = new JCheckBox("Add Matt");
        mattButton.setSelected(false);
        mattButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mattButton.isSelected()) {
                    newImageInfo.setIsMatted(true);
                } else {
                    newImageInfo.setIsMatted(false);
                }
                System.out.println("Set isMatted = " + newImageInfo.getIsMatted());
            }
        });

        captionArea = new JTextField("Enter a caption", 50);
        captionArea.setMargin(new Insets(5, 5, 5, 5));
        captionArea.setEditable(true);

        addCaptionButton = new JButton("Save Caption");
        addCaptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String caption = "";
                caption = captionArea.getText() + "\n";

                if (caption.length() != 0) {
                    newImageInfo.setCaption(caption);
                }

                System.out.println("Caption saved: " + newImageInfo.getCaption());
            }
        });

        inputPanel.add(selectFileButton);
        inputPanel.add(logScrollPane);
        inputPanel.add(frameButton);
        inputPanel.add(mattButton);
        inputPanel.add(captionArea);
        inputPanel.add(addCaptionButton);
        inputPanel.setPreferredSize(new Dimension(500, 100));

        //--------------------------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        uploadButton = new JButton("Upload");
        uploadButton.setActionCommand("upload");
        //uploadButton.addActionListener(listener);
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                galleryDBA.saveNewImage(newImageInfo);
                resetPanel();
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusArea.append("Cancelled\n");
                frameButton.setSelected(false);
                mattButton.setSelected(false);
                captionArea.setText("");
                newImageInfo = new ImageInfo();
            }
        });

        buttonPanel.add(uploadButton);
        buttonPanel.add(cancelButton);

        //------------------------------------------
        add(inputPanel);
        add(buttonPanel);
    }

    public void resetPanel() {
        statusArea.setText("");
        frameButton.setSelected(false);
        mattButton.setSelected(false);
        captionArea.setText("");
        newImageInfo = new ImageInfo();
        setVisible(true);
    }

    public ImageInfo getNewImageInfo() {
        return this.newImageInfo;
    }
}
