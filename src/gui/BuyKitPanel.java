package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**The class that handles display details and purchase of kits saved in the system.
 * @author Varada Gurjar*/
public class BuyKitPanel extends JPanel
{
	private PrecompiledKitGUI kitList;
	private JLabel summary;
	
	/**Constructor of the class.
	 *@param mediator GUIPanelMediator object.*/
	public BuyKitPanel(GUIPanelMediator mediator)
	{		
		summary = new JLabel("");
		summary.setFont(new Font("Calibri",Font.BOLD,16));
		summary.setMaximumSize(new Dimension(800,2000));
		
		mediator.setSummaryPanel(this);
		kitList = new PrecompiledKitGUI(mediator);
		kitList.setPurchase();
		kitList.setMaximumSize(new Dimension(2000,1000));
		
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(kitList);
		this.add(Box.createRigidArea(new Dimension(20,0)));
		this.add(summary);		
	}
	
	/**Sets the text of the summary label. Used to display kit contents.*/
	public void setText(String summary)
	{
		this.summary.setText(summary);
	}
}
