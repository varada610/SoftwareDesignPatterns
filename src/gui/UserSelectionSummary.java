package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**Class handles the tabbed menu of colors,brushes,tools etc in the art kit that a user can order.
 * @author Varada Gurjar*/
public class UserSelectionSummary extends JPanel {
	private JLabel summary;

	public UserSelectionSummary(GUIPanelMediator mediator) {
		
		summary = new JLabel("");
		summary.setFont(new Font("Calibri", Font.BOLD, 16));
		summary.setPreferredSize(new Dimension(800, 1000));

		JTabbedPane tabbedMenu = new JTabbedPane();
		tabbedMenu.setPreferredSize(new Dimension(1000, 500));
		
		PrecompiledKitGUI kitUI = new PrecompiledKitGUI(mediator);
		//mediator.setKitPanel(kitUI);
		
		Border margin = new EmptyBorder(10,20,0,0);
		tabbedMenu.setBorder(margin);

		tabbedMenu.addTab("Color", new Color(mediator));
		tabbedMenu.addTab("Brushes", new BrushSelection(mediator));
		tabbedMenu.addTab("Graphite Pencils", new GraphitePencilPanel(mediator));
		tabbedMenu.addTab("Tools", new ToolsPanel(mediator));
		tabbedMenu.addTab("Saved Kits", kitUI);

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(tabbedMenu);
		this.add(Box.createRigidArea(new Dimension(20, 0)));
		this.add(summary);
	}

	public void setText(String summary) {
		this.summary.setText(summary);
	}

}
