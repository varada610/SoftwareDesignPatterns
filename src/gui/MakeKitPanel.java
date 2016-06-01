package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**Handles constructing of a kit using user order details.
 * @author Varada Gurjar*/
public class MakeKitPanel extends JPanel implements ActionListener
{

private GUIPanelMediator mediator;
private SizeLevelGUI dropDownPanel;
private UserSelectionSummary summaryLabel;
private JPanel namePanel;
private SavePurchaseGUI purchase;
private JTextField kitName;

/**Constructor of the class
 * @param mediator GUIPanelMediator object.*/
public MakeKitPanel(GUIPanelMediator mediator)
{
	this.mediator = mediator;	
	
	this.dropDownPanel = new SizeLevelGUI(this.mediator);
	this.dropDownPanel.setMaximumSize(new Dimension(2000,65));
	
	//CONTAINED PANEL
	namePanel = new JPanel();
	
	kitName = new JTextField();
	kitName.setMaximumSize(new Dimension(200,55));
	kitName.setFont(new Font("Calibri",Font.BOLD,16));
	kitName.setHorizontalAlignment(JTextField.CENTER);
	
	JButton enterName = new JButton("Enter Kit Name");
	enterName.setFont(new Font("Calibri",Font.BOLD,16));
	enterName.addActionListener(this);
	enterName.setActionCommand("enter");
	
	namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.X_AXIS));
	namePanel.add(Box.createRigidArea(new Dimension(250,0)));
	namePanel.add(kitName);
	namePanel.add(Box.createRigidArea(new Dimension(70,0)));
	namePanel.add(enterName);		
	
	this.summaryLabel = new UserSelectionSummary(this.mediator);
	this.summaryLabel.setMaximumSize(new Dimension(2000,700));
	this.mediator.setSummaryPanel(summaryLabel);	
	
	purchase = new SavePurchaseGUI(this.mediator);
	purchase.setMinimumSize(new Dimension(2000,75));
	purchase.setCurrentPanel(this);
	
	this.setLayout();
}

/**Sets the layout of the panel*/
private void setLayout()
{	
	this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	this.add(dropDownPanel);
	this.add(this.namePanel);
	this.add(Box.createRigidArea(new Dimension(0,10)));
	this.add(this.summaryLabel);
	this.add(purchase);	
}

/**Events handled after button clicks on the panel
 * @param event the action events initiated by buttons.*/
@Override
public void actionPerformed(ActionEvent event) 
{
	String command = event.getActionCommand();
	
	if(command.equals("enter"))
	{
		String name = this.kitName.getText();
		
		if(!name.equals(""))
		this.mediator.setProductName(name);
	}
	
}

}
