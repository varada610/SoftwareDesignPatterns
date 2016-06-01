package gui;
import gallery.GalleryPanel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**The home page of the application where user can make/buy a kit, or view gallery.
 * *@author Varada Gurjar*/
public class HomePage extends JPanel implements ActionListener
{
	private JButton buyAKit;
	private JButton makeKit;
	private GUIPanelMediator mediator;
	private JButton gallery;	
	
	/**Constructor of the home page
	 * @param mediator GUIPanelMediator object.*/
	public HomePage(GUIPanelMediator mediator)
	{
		this.mediator = mediator;
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border margin = new EmptyBorder(60,40,40,40);
		this.setBorder(BorderFactory.createCompoundBorder(raisedbevel, margin));		
		
		this.buyAKit = new JButton("Buy a Kit");	
		this.buyAKit.addActionListener(this);
		this.buyAKit.setActionCommand("buy");
		this.buyAKit.setMaximumSize(new Dimension(500,60));
		this.buyAKit.setFont(new Font("Calibri",Font.BOLD,16));
		this.buyAKit.setHorizontalAlignment(JButton.CENTER);
		
		this.gallery = new JButton("View Gallery");	
		this.gallery.addActionListener(this);
		this.gallery.setActionCommand("gallery");
		this.gallery.setMaximumSize(new Dimension(500,60));
		this.gallery.setFont(new Font("Calibri",Font.BOLD,16));
		this.gallery.setHorizontalAlignment(JButton.CENTER);
		
		this.makeKit = new JButton("Make your Kit");
		this.makeKit.addActionListener(this);
		this.makeKit.setActionCommand("make");
		this.makeKit.setMaximumSize(new Dimension(400,60));
		this.makeKit.setFont(new Font("Calibri",Font.BOLD,16));
		this.makeKit.setHorizontalAlignment(JButton.CENTER);
		
		this.setLayout();
	}
	
	/**Sets the layout of the panel*/
	private void setLayout()
	{
		this.setLayout(new FlowLayout());
		this.add(Box.createRigidArea(new Dimension(130,0)));
		this.add(this.buyAKit);
		this.add(Box.createRigidArea(new Dimension(50,0)));
		this.add(this.makeKit);
		this.add(Box.createRigidArea(new Dimension(50,0)));
		this.add(this.gallery);	
	}

	/**Events handled after button clicks on the panel
	 * @param event the action events initiated by buttons.*/
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		if(command.equals("buy"))
		{
			this.setVisible(false);			
			
			JPanel buyPanel = new JPanel();

			BuyKitPanel buy = new BuyKitPanel(this.mediator);
			buy.setMaximumSize(new Dimension(2000,800));
			
			BackPanel back = new BackPanel(mediator);
			back.setMaximumSize(new Dimension(2000,60));
			back.setCurrentPanel(buyPanel);
			
			buyPanel.setLayout(new BorderLayout());
			buyPanel.add(buy,BorderLayout.CENTER);
			buyPanel.add(back,BorderLayout.SOUTH);

			buyPanel.setVisible(true);	
	        this.mediator.setPanel(buyPanel);
		}
		if(command.equals("make"))
		{
			this.setVisible(false);
			MakeKitPanel make = new MakeKitPanel(this.mediator);
			make.setVisible(true);
			this.mediator.setPanel(make);
		}
		if(command.equals("gallery"))
		{
			this.setVisible(false);
			GalleryPanel gallery = new GalleryPanel(this.mediator.getSessionObject());
			gallery.setVisible(true);
			this.mediator.setPanel(gallery);
		}
	}
}