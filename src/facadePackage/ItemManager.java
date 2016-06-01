package facadePackage;

import implementation.*;

import java.util.ArrayList;
import java.util.Collections;

import compositePatternPackage.Kit;
import java.text.DecimalFormat;

import singleton.KitRepository;

/**Facade Pattern class insulates the gui calls to packages. Directs gui calls and reduces coupling.
*@author Varada*/
public class ItemManager 
{
	private KitRepository commonRepository;
	private KitMaker maker;
	
	/**Constructor of the facade. Gets the repository instance.*/
	public ItemManager()
	{
		commonRepository = KitRepository.getInstance();
	}
	
	/**Retrieves a string with description of the requested kit contents. 
	 * This string is displayed on the gui summary panels.*/
	public String showDetails(String kitRequested)
	{
		Kit kit = this.retrieveFromRepository(kitRequested);
		if(kit != null)
		{
		
                    DecimalFormat df = new DecimalFormat("$#,##0.00");
                    String kitDetails = kit.showDetails();	
                
		kitDetails = kitDetails + "<br><br>Total cost : "+ df.format(kit.getCost());
		return kitDetails;
		}
		else 
			return "";
		
	}
	
	/**Returns the string array of kit names in the repository based upon account type.
	 * @param accountType can be 'student' or 'teacher'.
	 * @return String[] array of kit names.*/
	public String[] getKitKeys(String accountType)
	{
		String[] commonKeys = null, studentKeys = null;
		int length1 = 0, length2=0;
		
		ArrayList<String> both; 
				
		if(isAStudent(accountType))
		{
                    System.out.println("getKitKeys: using student path");
			this.commonRepository.setStudentRepositoryPath();
			studentKeys = this.commonRepository.getKeys();
			length1 = studentKeys.length;
		} 	
                this.commonRepository.setCommonRepositoryPath();
                
                commonKeys = this.commonRepository.getKeys();
                length2 = commonKeys.length;
		
		both = new ArrayList<String>(length1+length2);
		if(length1>0)
			Collections.addAll(both, studentKeys);
		
	    Collections.addAll(both, commonKeys);
		
		return both.toArray(new String[both.size()]);
	}
	
	/** Kit object added to repository based upon account type.
	 * @param accountType can be 'student' or 'teacher'.
	 * @param kitName name of the kit to add.
	 * @param kit the kit object to be added.*/
	public void addToRepository(String accountType,String kitName, Kit kit)
	{
		if(isAStudent(accountType))                
			this.commonRepository.setStudentRepositoryPath();
         else 
			this.commonRepository.setCommonRepositoryPath();              
			
		this.commonRepository.addToRepository(kitName, kit);
	}
	
	/**Check if user is student 
	 * @param accountType can be 'student' or 'teacher'
	 * @return true if the account is of a student.*/
	public boolean isAStudent(String accountType)
	{
		if(accountType.equals("student"))
			return true;
		
		else return false;
	}
	
	/**Retrieves the kit requested.
	 * @param kitRequested name of the kit requested.
	 * @return Kit the kit retrieved.*/
	public Kit retrieveFromRepository(String kitRequested)
	{
		Kit kit = this.commonRepository.retrieveKitFromRepository(kitRequested);
		
		if(kit != null)
			return kit;
		else 
			return null;
	}
	
	/**Creates the kit using user order details and product category selected.
	 * @param orderDetails user order details
	 * @param productSelected product category selected
	 * @return Kit the kit created.*/
	public Kit createKit(String productSelected, CustomizedKitOrderDetails orderDetails)
	{
		Kit myKit = null ;
		if(productSelected.equals("Acryllic Product"))
		{
			if(orderDetails != null)
			{
			this.maker = new AcrylicPaintingKitMaker(orderDetails);
			myKit = this.maker.makeKit();
			}
		}
        if(productSelected.equals("Pencil Drawing"))
		{
			if(orderDetails != null)
			{
			this.maker = new PencilDrawingKitMaker(orderDetails);
			myKit = this.maker.makeKit();
			}
		}
        return myKit;
	}

}
