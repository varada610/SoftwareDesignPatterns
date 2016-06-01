package gui;

import facadePackage.ItemManager;
import implementation.CustomizedKitOrderDetails;

import java.util.ArrayList;

import javax.swing.JPanel;

import loginPackage.Session;

import compositePatternPackage.AdvancedLevelState;
import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.Kit;
import compositePatternPackage.LargeSizeState;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.BrushTip;
import compositePatternPackage.productPackage.GraphiteGrade;
import compositePatternPackage.productPackage.Shade;
import databaseAccessPackage.AccountAccess;

/** The mediator between JPanels in the gui package
 * *@author Varada Gurjar*/
public class GUIPanelMediator 
{
	private CustomizedKitOrderDetails orderDetails=null;
	
	private SizeState size;
	private ExpertiseLevelState level;
	private String productName;
	private String productSelected;
	
	private ArrayList<Shade> mycolors;
	private ArrayList<BrushTip> myBrushes;
    private ArrayList<GraphiteGrade> myGraphitePencils;
	private ArrayList<String> myTools;
	private ArrayList<Kit> savedKit;	
	
	private UserSelectionSummary summary;
	private BuyKitPanel kitListPanel;

	private GUIFrame frame;
	private ItemManager kitManager;
	private Session session;

	private AccountAccess account;
	
	public GUIPanelMediator(GUIFrame frame)
	{
		this.frame = frame;
		
		this.orderDetails = new CustomizedKitOrderDetails("");
		this.kitManager = new ItemManager();
		this.size = null;
		this.level = null;
		this.productName = "";
		this.orderDetails.setBaseMaterialsToMake(1);
		this.mycolors = new ArrayList<Shade>();
		this.myBrushes = new ArrayList<BrushTip>();
        this.myGraphitePencils = new ArrayList<GraphiteGrade>();
		this.myTools = new ArrayList<String>();
		this.savedKit = new ArrayList<Kit>();
		this.account = new AccountAccess();
	}
	
	public void setPanel(JPanel panel)
	{
		this.frame.setPanel(panel);		
	}
	
	public void setSize(String selectionSize)
	{
		if(selectionSize == "Small")										
			this.size =new SmallSizeState();
		else
			this.size = new LargeSizeState();
	}
	
	public void setLevel(String selectionLevel)
	{
		if(selectionLevel == "Beginner")										
			level =new BeginnerLevelState();
		else
			level = new AdvancedLevelState();		
	}
	
	public AccountAccess getAccountAccessDBO()
	{
		return this.account;
	}
	
	public void setBaseMaterialQuantity(Integer quantity)
	{
		this.orderDetails.setBaseMaterialsToMake(quantity);
	}
	
	public void addColor(Shade shade)
	{
		this.mycolors.add(shade);
	}
	
	public void removeAllColor()
	{
		this.mycolors = new ArrayList<Shade>();
	}
	
	public void setSummaryPanel(UserSelectionSummary panel)
	{
		this.summary = panel;
	}
	
	public void setSummaryPanel(BuyKitPanel panel)
	{
		this.kitListPanel = panel;
	}
	
	public void setSessionObject(Session session)
	{
		this.session = session;
	}
	
	public Session getSessionObject()
	{
		return this.session;
	}
	
	public void addSavedKit(Kit kit)
	{
		this.savedKit.add(kit);
	}
	
	public void removeSavedKit(Kit kit)
	{
		this.savedKit.remove(kit);
	}
	
	public void setUserSelectionSummary(String summary)
	{
		summary = "<html>"+ summary + "</html>";

		if(this.summary!=null)
		{
		this.summary.setText("");		
		this.summary.setText(summary);
		}		
		if(this.kitListPanel != null)
		{
		this.kitListPanel.setText("");
		this.kitListPanel.setText(summary);
		}
	}
	
	public void removeAllBrushes()
	{
		this.myBrushes = new ArrayList<BrushTip>();
	}
        
        public void removeAllGraphitePencils()
	{
		this.myGraphitePencils = new ArrayList<GraphiteGrade>();
	}
	
	public void removeAllTools()
	{
		this.myTools = new ArrayList<String>();
	}
	
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	
	public void setProductSelected(String product)
	{
		this.productSelected = product;
	}
		
	public String getOrderDetails()
	{
		String orderDetails = "";
		
		if(mycolors != null && myBrushes!= null && myGraphitePencils!= null && myTools!=null && savedKit != null)
			orderDetails = this.orderDetails.showKitOrderDetails(mycolors, myBrushes, myGraphitePencils, myTools, savedKit);
		
		return orderDetails;
	}
	
	public void addBrush(BrushTip brushName)
	{
		this.myBrushes.add(brushName);
	}
        
        public void addGraphitePencil(GraphiteGrade grade)
	{
		this.myGraphitePencils.add(grade);
	}
	
	public void addTools(String tool)
	{
		this.myTools.add(tool);
	}
	
	public void save()
	{
		Kit myKit = this.finalizeOrderDetails();
		this.kitManager.addToRepository(this.session.getAccountType(), this.productName, myKit);
		this.resetOrderDetails();
	}
	
	public void showDetails(String kitName)
	{
		this.setUserSelectionSummary(this.kitManager.showDetails(kitName));
	}
	
	public Kit retrieveFromRepository(String kitRequested)
	{
		return this.kitManager.retrieveFromRepository(kitRequested);
	}
	
	public String[] getKeys()
	{
		return this.kitManager.getKitKeys(this.session.getAccountType());
	}
        
        public void resetOrderDetails()
        {
            this.removeAllColor();
            this.removeAllBrushes();
            this.removeAllGraphitePencils();
            this.removeAllTools();
            this.savedKit = new ArrayList<Kit>();
            
            this.size = null;
            this.level = null;
            this.productName = "";
        }

	public Kit finalizeOrderDetails()
	{
        this.orderDetails.setKitName(productName);
		this.orderDetails.setSize(this.size);
		this.orderDetails.setLevel(this.level);
		this.orderDetails.setColorsToMake(this.mycolors);
		this.orderDetails.setBrushesToMake(this.myBrushes);
        this.orderDetails.setGraphitePencilsToMake(this.myGraphitePencils);
		this.orderDetails.setToolsToMake(this.myTools);
		this.orderDetails.setSavedKitsToAdd(this.savedKit);
		
		Kit myKit = null;
		myKit = this.kitManager.createKit(productSelected,this.orderDetails);
        return myKit;
	}
	
	public void logout()
	{
		frame.logout();
	}
}