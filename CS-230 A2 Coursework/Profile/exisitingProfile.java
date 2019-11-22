import java.util.HashMap;
import java.util.Set;
/**
* This is a subclass of Profile which store the information.
* @author 
* @version 1.0
*/
public class exisitingProfile extends Profile {
	private int Score;
	/**
	* extending for the Profile
	*/
	public exisitingProfile(String name, int Score) {
		super(name);
		this.Score = 0;
		
	}
	static HashMap<String, Integer>playerprofile = new HashMap<>();
     
	/**
	* the method to add a new playerprofile
	*/
	public static void add(String name, int Score) {
		playerprofile.put(name,Score);
	    
	}

	/**
	* Create a way to get score 
	*/
	public static Integer load(String name){
		if( playerprofile.get(name)==null) {
			return -1;
			
		}
		else{
			return playerprofile.get(name);
		}
		
	}

	
	/**
	* a way to remove name 
	*/
	public static void gone(String poorname){
		playerprofile.remove(poorname);
	}
	/**
	* a way to clear the information
	*/
	public static void restart(){
		playerprofile.clear();
}

}