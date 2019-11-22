/**
 * This is a class that holds commom information about 'existingProfile' and new 'newProfile' class.
 * @author 
 * @version 1.0
 */
public class Profile {

	
		private String playername ;
		private String highestLevel ;
		
		private int highestScore;
		/**	
		* Create a Profile
		*/
    public Profile (String name )
    {
    	this.playername = name;
    
    	
    }
    /**
     * get the player name
     * @return palyername
     */
    public String getName(){
    	return this.playername;
    }
	/**
	*A fouction to save Profile
	*/
    public void saveProfile (String playername,int currentScore){
    	exisitingProfile.add(playername,currentScore);
    	
    }

	/**
	*A mothed to load the name and score of player
	* @return the new name of exisitingProfile.load
	*/
    public void loadProfile(String newname){
    	if (exisitingProfile.load(newname)==-1){
    		System.out.println("no information");
    	}
    	else{
    		System.out.println(exisitingProfile.load(newname));
    	}
    }
	/**
	* A mothed to change the name of playername
	*/
    public void changeName(String oldname,String changename){
    
    	int r;
    	r = exisitingProfile.load(oldname);
    	exisitingProfile.gone(oldname);
    	exisitingProfile.add(changename, r);
    	
    }
	/**
	* This a fouction to delete a profile
	*/
    public void deleteProfile(String deadname){
    	exisitingProfile.gone(deadname);
    }
	/**
	* A method to make the restart
	*/
    public void reset(){
    	exisitingProfile.restart();
    }
	/**
	* find the highestScore
	*/
    public void newhighestScore(int number){
    	number=highestScore;
    }
    
	}
 