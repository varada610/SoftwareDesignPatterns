package gui;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import compositePatternPackage.productPackage.BrushTip;
import compositePatternPackage.productPackage.Shade;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**Class handles the varied types of colors in the art kit that a user can order.
 * @author Varada Gurjar*/
public class Color extends JPanel implements ActionListener
{
private JTextField red, blue, green;
private JButton rButton,gButton,bButton;
private JButton clearAll;

private GUIPanelMediator mediator;
private JPanel redPanel,bluePanel,greenPanel;

/**Constructor of Color class.
 * @param mediator GUIPanelMediator object.*/
public Color(GUIPanelMediator mediator)
{
	this.mediator = mediator;
	
	this.setFont(new Font("Calibri",Font.BOLD,16));
	
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border margin = new EmptyBorder(10,20,0,0);
	this.setBorder(BorderFactory.createCompoundBorder(raisedbevel, margin));
	
	this.clearAll = new JButton("Clear Selection");
	this.clearAll.setFont(new Font("Calibri",Font.BOLD,16));
	this.clearAll.addActionListener(this);
	this.clearAll.setActionCommand("clear");
	
	this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	for(int index=0;index<Shade.values().length;index++)
	{
		String colorName = Shade.values()[index].toString();
		JButton button = new JButton("Add "+colorName);
		button.setFont(new Font("Calibri",Font.BOLD,16));
		button.addActionListener(this);
		button.setActionCommand(colorName);
		this.add(button);	
		this.add(Box.createRigidArea(new Dimension(0,8)));
	}
	this.add(Box.createRigidArea(new Dimension(0,30)));
	this.add(clearAll);
}

/**Sets the mediator of the panel*/
public void setGUIPanelMediator(GUIPanelMediator mediator)
{
	this.mediator = mediator;
}

/**Events handled after button clicks on the panel
 * @param event the action events initiated by buttons.*/
@Override
public void actionPerformed(ActionEvent event) 
{
	String command = event.getActionCommand();
	
	if(command.equals("clear"))
		this.mediator.removeAllColor();
	else
		this.mediator.addColor(Shade.valueOf(command));	
	
	this.mediator.setUserSelectionSummary(this.mediator.getOrderDetails());	
}

}
