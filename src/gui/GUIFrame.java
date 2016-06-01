package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import loginPackage.Session;



/**This class represents the main JFrame that contains the gui JPanels.
* @author Varada Gurjar*/
public class GUIFrame extends JFrame
{
	private JPanel currentPanel=null;
	private JPanel previousPanel=null;
	private GUIPanelMediator mediator;
	
	/** Constructor of the frame.
	*The main JFrame is instantiated using a session object
	@param session Session object*/
	public GUIFrame(Session session)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mediator = new GUIPanelMediator(this);
		mediator.setSessionObject(session);
		currentPanel = new HomePage(mediator);
				
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border lowBevel = BorderFactory.createLoweredBevelBorder();
		Border margin = new EmptyBorder(10,40,10,10);
		LogOutPanel logOut = new LogOutPanel(mediator);
		logOut.setUserName(session.getUserName());
		logOut.setMaximumSize(new Dimension(2000,65));
		logOut.setBorder(BorderFactory.createCompoundBorder(raisedbevel, margin));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		AccountPanel account = new AccountPanel(mediator);
		account.setMaximumSize(new Dimension(2000,65));
		account.setBorder(BorderFactory.createCompoundBorder(raisedbevel, lowBevel));
		account.setAmount(mediator.getSessionObject().getAccountBalance());
		account.setLogTime(mediator.getSessionObject().getPrevLoginTime().toString());
		panel.add(logOut,BorderLayout.NORTH);
		panel.add(account,BorderLayout.SOUTH);
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(panel,BorderLayout.NORTH);
		contentPane.add(currentPanel,BorderLayout.CENTER);
	}
	
	/**JPanels within the frame are set within callback to back button
	* @param panel the panel to be set.*/
	public void setPanel(JPanel panel)
	{
		this.getContentPane().remove(this.currentPanel);
		this.getContentPane().add(panel);
		this.previousPanel = this.currentPanel;	
		this.currentPanel = panel;		
	}
	
	/**Invoked by all panels in the gui to logout.*/
	public void logout()
	{
		this.setVisible(false);
		login();
	}
	
	/**Called from the main method to login.*/
	public static void login()
	{
		Login login = Login.getInstance();
    	login.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	login.setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
    	login();
	}
}
