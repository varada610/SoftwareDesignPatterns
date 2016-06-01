package gallery;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel for grading artifacts (used by teacher)
 *
 * @author Aditee Nagle
 */
public class GradeArtifactPanel extends JPanel {

    ActionListener listener;
    JTextField gradeField;
    JButton saveGradeButton;
    private JButton back;

    public GradeArtifactPanel(ActionListener listener) {
        super(new FlowLayout());
        this.listener = listener;
        setPanelContents();
        setVisible(true);
    }

    private void setPanelContents() {
        DecimalFormat df = new DecimalFormat("0.0");
        gradeField = new JTextField(10);
        gradeField.setMargin(new Insets(5, 5, 5, 5));
        gradeField.setEditable(true);

        saveGradeButton = new JButton("Save Grade");
        saveGradeButton.setActionCommand("grade");
        saveGradeButton.addActionListener(listener);

        back = new JButton(" Back ");
        back.setActionCommand("back");
        back.addActionListener(listener);

        add(gradeField);
        add(saveGradeButton);
        add(back);
    }

    public double getEnteredGrade() {
        double gradeValue = Double.parseDouble(gradeField.getText());
        System.out.println("Grade value = " + gradeValue);
        return gradeValue;
    }

    public void resetPanel() {
        gradeField.setText("");
        setVisible(true);
    }
}
