package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import compositePatternPackage.productPackage.*;

/**Class handles the varied types of brushes in the art kit that a user can order.
 * @author Varada Gurjar*/
public class BrushSelection extends JPanel implements ActionListener
{
	private GUIPanelMediator mediator;	
	private JButton clearAll;
	
	/**Constructor of Brush class.
	 * @param mediator GUIPanelMediator object.*/
	public BrushSelection(GUIPanelMediator mediator)
	{
		this.mediator = mediator;
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border margin = new EmptyBorder(10,20,0,0);
		this.setBorder(BorderFactory.createCompoundBorder(raisedbevel, margin));
		
		this.clearAll = new JButton("Clear Selection");
		this.clearAll.setFont(new Font("Calibri",Font.BOLD,16));
		this.clearAll.addActionListener(this);
		this.clearAll.setActionCommand("clear");
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		for(int index=0;index < BrushTip.values().length;index++)
		{
			String brushName = BrushTip.values()[index].toString();			
			JButton brush = new JButton();
			brush.setFont(new Font("Calibri",Font.BOLD,16));			
			brush.addActionListener(this);
			brush.setActionCommand(brushName);
			
			brushName = brushName.replaceAll("_", " ");
			brush.setText("Add "+brushName);
			this.add(brush);
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
		
		if(command.equals("clear"))
			this.mediator.removeAllBrushes();		
		else
			this.mediator.addBrush(BrushTip.valueOf(command));
		
		this.mediator.setUserSelectionSummary(this.mediator.getOrderDetails());
	}

}
