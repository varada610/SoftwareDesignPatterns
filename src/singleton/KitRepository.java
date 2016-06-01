package singleton;
import java.io.*;
import compositePatternPackage.*;;

/** Singleton class that manages the serializing and deserializing of Kit class objects.
 * Serialized files are common Kit objects created by teachers, or kits saved by students.
 * @author Varada Gurjar*/
public class KitRepository 
{
	private static KitRepository commonRepository=null;
	private static String currentPath;
	//private static String repositoryPath = KitRepository.class.getResource(".").getPath() +"res/";
	//private static String studentRepositoryPath = KitRepository.class.getResource(".").getPath() +"studentres/";
    private static String repositoryPath = System.getProperty("user.dir")+ "\\build\\classes\\singleton\\res\\";
    private static String studentRepositoryPath = System.getProperty("user.dir")+ "\\build\\classes\\singleton\\studentres\\";
	/**Returns an instance of this singleton class. 
	 * @return KitRepository singleton object.*/
	public static KitRepository getInstance()
	{
		currentPath = repositoryPath;
		if(commonRepository == null)
		{
			commonRepository = new KitRepository();
		}
		
		return commonRepository;
	}
	
	/**Returns string list of names of kits stored at the current path.
	 * @return String[] kit names.*/
	public String[] getKeys()
	{			
		return (new File(currentPath)).list();		
	}
	
	/**Sets the current working repository path to the common repository path.
	 * Invoked by the ItemManager (Facade).*/
	public void setCommonRepositoryPath()
	{
		currentPath = repositoryPath;
	}
	
	/**Sets the current working repository path to the student's repository path.
	 * Invoked by the ItemManager (Facade).*/
	public void setStudentRepositoryPath()
	{
		currentPath = studentRepositoryPath;
	}
	
	/**The kit is added as a serialized object to the repository at the current working path
	 * @param kitName Name of the kit to be added to path 
	 * @param kit The kit object to be added.
	 * @return boolean returns true if duplicate kit exits.*/
	public boolean addToRepository(String kitName, KitComponent kit)
	{
		boolean bool = true;
		
		bool = this.isDuplicate(kitName);
		System.out.println(bool + kitName );
		 try
	      {
	         FileOutputStream fileOut =  new FileOutputStream(currentPath + kitName);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(kit);
	         out.close();   fileOut.close();
	      }
		 catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		 
		 return bool;
	}
	
	/**Retrieve kit from repository with the kit name provided.
	 * @param kitName name of the kit.
	 * @return Kit Kit class object*/
	public Kit retrieveKitFromRepository(String kitName)
	{	
		Kit kit = null;
		this.setCommonRepositoryPath();
		try 
		{
			FileInputStream fileIn = new FileInputStream(currentPath + kitName);
			ObjectInputStream in = new ObjectInputStream(fileIn);		
			
			kit = (Kit) in.readObject();
			in.close();
		}
		catch(FileNotFoundException ex)
		{
			this.setStudentRepositoryPath();
			try
			{
			FileInputStream fileIn = new FileInputStream(currentPath + kitName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			kit = (Kit) in.readObject();
			in.close();
			}
			catch (FileNotFoundException e) {e.printStackTrace();}
			catch(IOException e){e.printStackTrace();}
			catch(ClassNotFoundException e)	{e.printStackTrace();}				
		}
		catch(IOException ex){ex.printStackTrace();}		
		catch(ClassNotFoundException ex) {ex.printStackTrace();}
		
		return kit;		      
	}
	
	/** Checks if the kit with kitname already exists in the repository.
	 *@return boolean returns true if duplicate kit exits.*/
	public boolean isDuplicate(String kitName)
	{
		File file = new File(currentPath+kitName);
		boolean bool = file.exists();
		
		return bool;
	}
}
