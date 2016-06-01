package gui;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;

import javax.swing.*;

import loginPackage.Authenticator;
import loginPackage.Authorizer;
import loginPackage.Session;

/**single point login entry : singleton class
 * Login for students and faculty.
 *@author Varada Gurjar*/
public class Login extends JFrame implements ActionListener
{
	private static Login login= new Login();
	private JPanel panel;
	
	private JLabel name;
	private JTextField userNameEntry;
	private JLabel password;
	private JPasswordField passwordEntry;
	
	private JButton loginEntry;
	private JButton close;
	private Session session;
	
	/**Constructor of the singleton Login panel*/
	private Login()
	{
		Container contentPane = getContentPane();	 
		 
		 setTitle("Login");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                   
		 setVisible(true);
		 
		 panel = new JPanel();
		 contentPane.add(panel);
		 
		 this.userNameEntry =  new JTextField("");
		 this.userNameEntry.setMaximumSize(new Dimension(200,45));
		 this.userNameEntry.setHorizontalAlignment(JTextField.CENTER);
		 this.userNameEntry.setFont(new Font("Calibri",Font.BOLD,16));
		 
		 this.name = new JLabel("Enter your user name :");
		 this.name.setFont(new Font("Calibri",Font.BOLD,16));
		 this.name.setHorizontalAlignment(JLabel.CENTER);
		 this.name.setAlignmentX(CENTER_ALIGNMENT);
		 
		 this.passwordEntry =  new JPasswordField("");
		 this.passwordEntry.setMaximumSize(new Dimension(200,45));
		 this.passwordEntry.setHorizontalAlignment(JTextField.CENTER);
		 this.passwordEntry.setFont(new Font("Calibri",Font.BOLD,16));
		 
		 this.password = new JLabel("Enter your password :");
		 this.password.setFont(new Font("Calibri",Font.BOLD,16));
		 this.password.setHorizontalAlignment(JLabel.CENTER);
		 this.password.setAlignmentX(CENTER_ALIGNMENT);
		 
		 this.loginEntry = new JButton("Login");
		 this.loginEntry.setFont(new Font("Calibri",Font.BOLD,16));
		 this.loginEntry.addActionListener(this);
		 this.loginEntry.setActionCommand("login");
		 
		 this.close = new JButton("Exit");
		 this.close.setFont(new Font("Calibri",Font.BOLD,16));
		 this.close.addActionListener(this);
		 this.close.setActionCommand("close");
		 
		 setLayout();
		 panel.setVisible(true);
		 
		 pack();
		 setLocationRelativeTo(null); 
	}
	
	/**Sets the layout of the panel*/
	private void setLayout()
	{
		 panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		 panel.add(Box.createRigidArea(new Dimension(0,130)));
		 panel.add(name);
		 panel.add(Box.createRigidArea(new Dimension(0,15)));
		 panel.add(userNameEntry);
		 panel.add(Box.createRigidArea(new Dimension(0,15)));
		 panel.add(password);
		 panel.add(Box.createRigidArea(new Dimension(0,15)));
		 panel.add(passwordEntry);
		 panel.add(Box.createRigidArea(new Dimension(0,35)));
		 panel.add(this.loginEntry);
		 panel.add(Box.createRigidArea(new Dimension(0,50)));
		 panel.add(this.close);
		 panel.add(new JLabel());
	}
	
	/**Returns the single login instance of the panel
	 * @return Login login panel instance*/
	public static Login getInstance()
	{
		return login;
	}

	/**Events handled after button clicks on the panel
	 * @param event the action events initiated by buttons.*/
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		if(command.equals("login"))
		{
			//authenticate using strategy pattern
			  String userName =  this.userNameEntry.getText();//"aditeen";
		        String password = this.passwordEntry.getText();//"password";
		        
			Authenticator authenticator = new Authenticator();
	        Authorizer authorizer;
	        session = null;
	        
	        java.util.Date date = new java.util.Date();
	        Timestamp current = new Timestamp(date.getTime());
	        System.out.println(current.toString());
	        if(authenticator.authenticate(userName, password)) {
	            authorizer = new Authorizer();
	            session = authorizer.authorize(userName);
	        }
	        if(session != null) {
	            System.out.println("UserName: " + session.getUserName());
	            System.out.println("Account Type: " + session.getAccountType());
	            System.out.println("Account balance: " + session.getAccountBalance());
	            System.out.println("Previous Login: " + session.getPrevLoginTime().toString());
	        
			
			//end test
			this.setVisible(false);
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() 
	            {
	            	GUIFrame frame = new GUIFrame(session);
	            	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);	         
	            	frame.setVisible(true);	            	
	            }
	        });
                }
		}	
		if(command.equals("close"))
		{
			this.setVisible(false);
			this.dispose();
		}
	}
}
