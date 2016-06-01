package gui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import compositePatternPackage.*;

/** Class responsible towards setting a size for kit and expertise level from gui.
 * *@author Varada Gurjar*/
public class SizeLevelGUI extends JPanel implements ActionListener
{
	private JComboBox<String> sizeOptions;
	private JComboBox<String> levelOptions;
	private JComboBox<String> mainCategory;
	private JComboBox<String> subCategory;
	private JComboBox<String> baseMaterials;
	private GUIPanelMediator mediator;
	
	/**Constructor of this panel
	 *@param mediator GUIPanelMediator object.*/
	public SizeLevelGUI(GUIPanelMediator mediator)
	{
		this.mediator = mediator;
		
		//Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border margin = new EmptyBorder(10,40,10,10);
		this.setBorder(margin);
		
		//SIZE : SMALL - LARGE
		sizeOptions = new JComboBox<String>();
		sizeOptions.setMaximumSize(new Dimension(150,45));
		sizeOptions.addItem("Select Size");
		sizeOptions.setFont(new Font("Calibri",Font.BOLD,16));
		for(int index=0;index<Size.values().length;index++)
		{
			sizeOptions.addItem(Size.values()[index].toString());
		}
		sizeOptions.addActionListener(this);
		sizeOptions.setActionCommand("size");
		
		//LEVEL : BEGINNER - ADVANCED 
		levelOptions = new JComboBox<String>();
		levelOptions.setMaximumSize(new Dimension(150,45));
		levelOptions.addItem("Select Level");
		levelOptions.setFont(new Font("Calibri",Font.BOLD,16));
		for(int index=0;index<Level.values().length;index++)
		{
			levelOptions.addItem(Level.values()[index].toString());
		}
		levelOptions.addActionListener(this);
		levelOptions.setActionCommand("level");
		
		//MAIN : PAINTING, PAPER
		mainCategory = new JComboBox<String>();
		mainCategory.setMaximumSize(new Dimension(300,45));
		mainCategory.setFont(new Font("Calibri",Font.BOLD,16));
		mainCategory.addItem("Select Product Category");
		mainCategory.addItem("Painting Product");
        mainCategory.addItem("Drawing Product");
		mainCategory.addActionListener(this);
		mainCategory.setActionCommand("main");
		
		//SUB : UNDER MAIN CATEGORY OPTIONS
		subCategory = new JComboBox<String>();
		subCategory.setMaximumSize(new Dimension(300,45));
		subCategory.setFont(new Font("Calibri",Font.BOLD,16));
		subCategory.addItem("Select Product Sub-Category");
		subCategory.setEnabled(false);
		subCategory.addActionListener(this);
		subCategory.setActionCommand("sub");
		
		//BASE MATERIAL ADD
		baseMaterials = new JComboBox<String>();
		baseMaterials.setMaximumSize(new Dimension(300,45));
		baseMaterials.setFont(new Font("Calibri",Font.BOLD,16));
		baseMaterials.addItem("Select number of Base Materials");
                baseMaterials.addItem("0");
		baseMaterials.addItem("1"); baseMaterials.addItem("2");
		baseMaterials.addItem("3"); baseMaterials.addItem("4");
		baseMaterials.addItem("5");
		baseMaterials.addActionListener(this);
		baseMaterials.setActionCommand("base");
		
		
		this.setLayout();
	}
	
	/**Sets the layout of the panel*/
	private void setLayout()
	{
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(Box.createRigidArea(new Dimension(100,0)));
		this.add(this.mainCategory);
		this.add(Box.createRigidArea(new Dimension(30,0)));
		this.add(this.subCategory);
		this.add(Box.createRigidArea(new Dimension(30,0)));
		this.add(this.sizeOptions);
		this.add(Box.createRigidArea(new Dimension(30,0)));
		this.add(this.levelOptions);
		this.add(Box.createRigidArea(new Dimension(30,0)));
		this.add(this.baseMaterials);			
	}

	/**Events handled after button clicks on the panel
	 * @param event the action events initiated by buttons.*/
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		String selection;
		switch(command)
		{
			case "size": 
				{  
					this.mediator.setSize((String)this.sizeOptions.getSelectedItem());	
                                        break;
				}
			case "level":
				{
					this.mediator.setLevel((String) this.levelOptions.getSelectedItem());
                                        break;
				}	
			case "main":
				{
					subCategory.setEnabled(true);
					subCategory.removeAllItems();
					subCategory.addItem("Select Product Sub-Category");
					selection = (String) this.mainCategory.getSelectedItem();
					if(selection == "Painting Product")
						subCategory.addItem("Acryllic Product");
                                        if(selection == "Drawing Product")				
                                                                     subCategory.addItem("Pencil Drawing");
                                        break;
                   
				}
			case "sub":
				{
					this.mediator.setProductSelected((String) this.subCategory.getSelectedItem());	
                                        break;
				}
			case "base":
				{
					selection = (String)this.baseMaterials.getSelectedItem();
					if(!selection.equals("Select number of Base Materials"))
					this.mediator.setBaseMaterialQuantity(Integer.valueOf(selection));
                                        break;
				}
		}	
	}	
}
