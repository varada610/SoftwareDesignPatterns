package gui;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**Panel in the GUIFrame to move back from current panel.
 * @author Varada Gurjar*/
public class BackPanel extends JPanel implements ActionListener 
{	
	private JButton back;
	private JPanel currentPanel;
	private GUIPanelMediator mediator;

	/**Constructor of the Panel
	 *@param mediator GUIPanelMediator object.*/
	public BackPanel(GUIPanelMediator mediator)
	{
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border margin = new EmptyBorder(5,0,5,20);
		this.setBorder(BorderFactory.createCompoundBorder(raisedbevel, margin));
		
	this.mediator = mediator;
	
	back = new JButton("Back");	
	back.setPreferredSize(new Dimension(200,45));
	back.setFont(new Font("Calibri",Font.BOLD,16));
	back.addActionListener(this);
	back.setActionCommand("back");
	
	this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
	this.add(back);
	}

	/**Events handled after button clicks on the panel
	 * @param event the action events initiated by buttons.*/
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		
		if(command.equals("back"))
		{
			this.currentPanel.setVisible(false);
			HomePage panel = new HomePage(mediator);
			panel.setVisible(true);
			this.mediator.setPanel(panel);			
		}		
	}
	
	/**Sets another new Panel as the current panel.*/
	public void setCurrentPanel(JPanel panel)
	{
		this.currentPanel = panel;
	}
}