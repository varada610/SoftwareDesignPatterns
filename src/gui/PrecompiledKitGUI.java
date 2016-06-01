package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import databaseAccessPackage.AccountAccess;

import facadePackage.*;

/**Handles the saved kit selection and purchase in art kit system.
 * *@author Varada Gurjar*/
public class PrecompiledKitGUI extends JPanel implements ActionListener
{
	private JComboBox<String> kitDropDown;
	private String[] kitList; 
	private JLabel label;
	private JButton select;
	
	private GUIPanelMediator mediator;
	private JButton showDetails;
	private JButton makePanelPurchase;
	private JButton buyPanelPurchase;
	
	/**Constructor of the saved kit gui. 
	 * @param mediator GUIPanelMediator object.*/
	public PrecompiledKitGUI(GUIPanelMediator mediator)
	{
		this.mediator = mediator;
		this.kitList = this.mediator.getKeys();
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border margin = new EmptyBorder(10,20,0,0);
		this.setBorder(BorderFactory.createCompoundBorder(raisedbevel, margin));
		
		this.kitDropDown = new JComboBox<String>(this.kitList);
		this.kitDropDown.setFont(new Font("Calibri",Font.BOLD,16));
		this.kitDropDown.setMaximumSize(new Dimension(400,40));
		
		this.label = new JLabel("Select kit from :");
		this.label.setFont(new Font("Calibri",Font.BOLD,16));
		this.label.setMaximumSize(new Dimension(200,40));
		
		this.select = new JButton("Select Kit");
		this.select.setMaximumSize(new Dimension(200,40));
		this.select.setFont(new Font("Calibri",Font.BOLD,16));
		this.select.addActionListener(this);
		this.select.setActionCommand("select");
		
		this.makePanelPurchase = new JButton("Purchase the Kit");
		this.makePanelPurchase.setMaximumSize(new Dimension(200,40));
		this.makePanelPurchase.setFont(new Font("Calibri",Font.BOLD,16));
		this.makePanelPurchase.addActionListener(this);
		this.makePanelPurchase.setActionCommand("purchase");
		
		this.buyPanelPurchase = new JButton("Purchase Kit");
		this.buyPanelPurchase.setMaximumSize(new Dimension(200,40));
		this.buyPanelPurchase.setFont(new Font("Calibri",Font.BOLD,16));
		this.buyPanelPurchase.addActionListener(this);
		this.buyPanelPurchase.setActionCommand("buy");
		
		this.showDetails = new JButton("Show kit contents");
		this.showDetails.setMaximumSize(new Dimension(200,40));
		this.showDetails.setFont(new Font("Calibri",Font.BOLD,16));
		this.showDetails.addActionListener(this);
		this.showDetails.setActionCommand("show");
		
		this.setLayout();		
	}
	
	/**Sets the layout of the panel*/
	private void setLayout()
	{
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(label);
		add(Box.createRigidArea(new Dimension(0,20)));
		this.add(this.kitDropDown);
		add(Box.createRigidArea(new Dimension(0,20)));
		this.add(this.showDetails);
		add(Box.createRigidArea(new Dimension(0,20)));
		this.add(this.select);		
	}
	
	/**Sets the gui mediator.*/
	public void setMediator(GUIPanelMediator mediator)
	{
		this.mediator = mediator;
	}
	
	/**Sets 'purchase' button in the buy kit and 'select' button in the make kit*/
	public void setPurchase()
	{
		this.remove(this.select);
		this.add(this.buyPanelPurchase);
	}

	/**Events handled after button clicks on the panel
	 * @param event the action events initiated by buttons.*/
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		String kitRequested = (String)this.kitDropDown.getSelectedItem();
		
		if(command.equals("select"))
					this.mediator.addSavedKit(this.mediator.retrieveFromRepository(kitRequested));
		
		if(command.equals("purchase"))
		{
			AccountAccess account = this.mediator.getAccountAccessDBO();	
			String userName = this.mediator.getSessionObject().getUserName();
			double currentBalance = account.getAccountBalance(userName);
			double kitCost = this.mediator.finalizeOrderDetails().getCost(); 
			if( currentBalance >= kitCost)
			{
				currentBalance = currentBalance - kitCost;
				account.setAccountBalance(userName, currentBalance);
			}
		}
		if(command.equals("buy"))
		{
			AccountAccess account = this.mediator.getAccountAccessDBO();	
			String userName = this.mediator.getSessionObject().getUserName();
			double currentBalance = account.getAccountBalance(userName);
			double kitCost = this.mediator.retrieveFromRepository(kitRequested).getCost();
			if( currentBalance >= kitCost)
			{
				currentBalance = currentBalance - kitCost;
				account.setAccountBalance(userName, currentBalance);
			}
		}
		
		if(command.equals("remove"))
					this.mediator.removeSavedKit(this.mediator.retrieveFromRepository(kitRequested));
		
		if(command.equals("show"))
					this.mediator.showDetails(kitRequested);
	}

}