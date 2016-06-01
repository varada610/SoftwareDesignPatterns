package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import databaseAccessPackage.AccountAccess;
import java.text.DecimalFormat;

/**GUI panel that handles the save or purchase of a newly created kit
 * @author Varada Gurjar*/
public class SavePurchaseGUI extends JPanel implements ActionListener
{
	private JButton save;
	private JButton back;
	private JButton purchase;
	
	private GUIPanelMediator mediator;
	private JPanel currentPanel;
	private JButton showCost;
	private JLabel cost;
	
	/**Constructor of this panel.
	 *@param mediator GUIPanelMediator object.*/
	public SavePurchaseGUI(GUIPanelMediator mediator)
	{
		this.mediator = mediator;
		this.setFont(new Font("Calibri",Font.BOLD,16));
		
		this.save = new JButton("Save");
		this.save.setMaximumSize(new Dimension(150,65));
		this.save.setFont(new Font("Calibri",Font.BOLD,16));
		this.save.addActionListener(this);
		this.save.setActionCommand("save");
		
		this.back = new JButton("Back");
		this.back.setMaximumSize(new Dimension(150,65));
		this.back.setFont(new Font("Calibri",Font.BOLD,16));
		this.back.addActionListener(this);
		this.back.setActionCommand("back");
		
		this.showCost = new JButton("Show Cost");
		this.showCost.setMaximumSize(new Dimension(150,65));
		this.showCost.setFont(new Font("Calibri",Font.BOLD,16));
		this.showCost.addActionListener(this);
		this.showCost.setActionCommand("cost");
		
		this.cost = new JLabel();
		this.cost.setMaximumSize(new Dimension(100,65));
		this.cost.setFont(new Font("Calibri",Font.BOLD,16));
		
		
		this.purchase = new JButton("Purchase");
		this.purchase.setMaximumSize(new Dimension(150,65));
		this.purchase.setFont(new Font("Calibri",Font.BOLD,16));
		this.purchase.addActionListener(this);
		this.purchase.setActionCommand("purchase");
				
		this.setLayout(new FlowLayout());
		this.add(back);		
		add(Box.createRigidArea(new Dimension(100,0)));
		this.add(save);
		add(Box.createRigidArea(new Dimension(100,0)));
		this.add(showCost);	
		add(Box.createRigidArea(new Dimension(100,0)));
		this.add(cost);	
		add(Box.createRigidArea(new Dimension(100,0)));
		this.add(purchase);		
	}
	
	/**Sets the current panel. Used for the back functionality.
	 * @param panel JPanel to be set.*/
	public void setCurrentPanel(JPanel panel)
	{
		this.currentPanel = panel;
	}

	/**Events handled after button clicks on the panel
	 * @param event the action events initiated by buttons.*/
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		
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
                        mediator.resetOrderDetails();
		}
		if(command.equals("cost"))
		{
                    //------------Aditee***-----------
                    if(this.mediator == null) {
                        System.out.println("Mediator Null");
                    } else {
                        if(this.mediator.finalizeOrderDetails() == null) {
                            System.out.println("FinalizeOrderDetails returned kit is null");
                        }
                    }
                    //------------Aditee***-----------
                    DecimalFormat df = new DecimalFormat("$#,##0.00");
			this.cost.setText(df.format(this.mediator.finalizeOrderDetails().getCost()));
		}
		if(command.equals("save"))
		{
			this.mediator.save();
			this.mediator.setUserSelectionSummary("");
		}
		if(command.equals("back"))
		{
		this.currentPanel.setVisible(false);
		HomePage panel = new HomePage(mediator);
		panel.setVisible(true);
		this.mediator.setPanel(panel);
		}
		
		
	}
}
