package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**Panel contains username and log out button for the application.
 * *@author Varada Gurjar*/
public class LogOutPanel extends JPanel implements ActionListener
{
	private JButton logOut;
	private GUIPanelMediator mediator;
	private JLabel userName;
	
	/**Constructor of the log out panel.
	 *@param mediator GUIPanelMediator object.*/
	public LogOutPanel(GUIPanelMediator mediator)
	{
		this.mediator = mediator;
		
		logOut = new JButton("Logout");	
		logOut.setMaximumSize(new Dimension(200,45));
		logOut.setFont(new Font("Calibri",Font.BOLD,16));
		logOut.addActionListener(this);
		logOut.setActionCommand("logout");
		
		userName = new JLabel("Name");	
		userName.setMaximumSize(new Dimension(400,45));
		userName.setFont(new Font("Calibri",Font.BOLD,16));
		userName.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		userName.setHorizontalAlignment(JLabel.RIGHT);
		
		this.setLayout();
	}
	
	/**Sets the layout of the panel*/
	private void setLayout()
	{
		this.setLayout(new BorderLayout());
		this.add(logOut,BorderLayout.EAST );
		this.add(userName,BorderLayout.CENTER);
	}
	
	/**Sets the user name of the in the application
	 * @param name User Name of user logged in.*/
	public void setUserName(String name)
	{
		this.userName.setText("Welcome "+name+"!      ");
	}


	/**Events handled after button clicks on the panel
	 * @param event the action events initiated by buttons.*/
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		
		if(command.equals("logout"))
		{
			mediator.logout();
		}		
	}
}