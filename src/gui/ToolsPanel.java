package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import compositePatternPackage.productPackage.BrushTip;
import compositePatternPackage.productPackage.ToolList;
import javax.swing.*;
import javax.swing.border.*;

import compositePatternPackage.productPackage.Shade;

/**Class handles the varied types of tools in the art kit that a user can order.
 * @author Varada Gurjar*/
public class ToolsPanel extends JPanel implements ActionListener
{
	private GUIPanelMediator mediator;
	private JButton clearAll;

	/**Constructor of this class.
	 * @param mediator GUIPanelMediator object.*/
	public ToolsPanel(GUIPanelMediator mediator)
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
	for(int index=0;index<ToolList.values().length;index++)
	{
		String tool = ToolList.values()[index].toString();			
		JButton toolButton = new JButton();
		toolButton.setFont(new Font("Calibri",Font.BOLD,16));			
		toolButton.addActionListener(this);
		toolButton.setActionCommand(tool);
		
		tool = tool.replaceAll("_", " ");
		toolButton.setText("Add "+tool);
		this.add(toolButton);
		add(Box.createRigidArea(new Dimension(0,8)));		
	}
	this.add(Box.createRigidArea(new Dimension(0,30)));
	this.add(clearAll);
	
	}

	/**Events handled after button clicks on the panel
	 * @param event the action events initiated by buttons.*/
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		if(command.equals("EASEL"))
		{
			this.mediator.addTools("easel");
		}
		if(command.equals("ERASER"))
		{
			this.mediator.addTools("eraser");
		}
		if(command.equals("REGULAR_PENCIL"))
		{
			this.mediator.addTools("pencil");
		}
		if(command.equals("PALETTE"))
		{
			this.mediator.addTools("palette");
		}
		if(command.equals("SHARPENER"))
		{
			this.mediator.addTools("sharpener");
		}
		if(command.equals("clear"))
		{
		this.mediator.removeAllColor();
		}	
		
		this.mediator.setUserSelectionSummary(this.mediator.getOrderDetails());	
	}

}
