package gui;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import databaseAccessPackage.AccountAccess;
import java.text.DecimalFormat;

/** Panel in the GUI that displays the balance amount. First display is through the database.
 * Future purchase deductions reflect upon the panel via Observer pattern.
 * @author Varada Gurjar*/
public class AccountPanel extends JPanel implements Observer
{
	private JLabel balance;
	private JLabel logTime;
	private GUIPanelMediator mediator;

	/**Constructor of Account Panel where the account balance is displayed. 
	 * @param mediator GUIPanelMediator object.*/
	public AccountPanel(GUIPanelMediator mediator)
	{
	this.mediator = mediator;
	
	this.mediator.getAccountAccessDBO().addObserver(this);
	balance = new JLabel("");	
	balance.setMaximumSize(new Dimension(400,45));
	balance.setFont(new Font("Calibri",Font.BOLD,16));
	
	logTime = new JLabel("");	
	logTime.setMaximumSize(new Dimension(400,45));
	logTime.setFont(new Font("Calibri",Font.BOLD,16));
	
	this.setLayout(new BorderLayout());
	this.add(balance,BorderLayout.EAST);
	this.add(logTime,BorderLayout.WEST);
	}
	
	/**Sets the amount in the Account Panel. Invoked by Observer Pattern.
	 * @param amount Amount to be set in the Panel.*/
	public void setAmount(double amount)
	{
            DecimalFormat df = new DecimalFormat("$#,##0.00");
		this.balance.setText("Your current balance is : " + df.format(amount));
	}
	
	/**Sets the last log time in the Account Panel. Sets the time at each user login.
	 * @param logTime Last log time to be set in the Panel.*/
	public void setLogTime(String logTime)
	{
		this.logTime.setText("Your last login time was : "+logTime);
	}

	/**Events handled after button clicks on the panel
	 * 
         */
	@Override
	public void update(Observable accountObject, Object arg1) 
	{
		AccountAccess account =(AccountAccess)accountObject;
		double amount = account.getAccountBalance(this.mediator.getSessionObject().getUserName());
		this.setAmount(amount);
		this.repaint();
	}
	
}
