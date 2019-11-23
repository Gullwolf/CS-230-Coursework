import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class represents a profile of a player that has already created an account.
 * @author Cai Sidaway
 *@version 1.0
 */
public class Profile {
	private static String username;
	private static String userPassword;
	private static int highestLevel;
	private static int currentScore;
	private static int highestScore;
	
	/**
	 * Creates a profile of the player that has logged in.
	 * @param uname - Username
	 * @param pass - Password
	 * @param curlev - Current level of player
	 * @param curscore - Current score of the player
	 * @param highscore - Current highscore
	 */
	public Profile(String uname, String pass, int curlev, int curscore, int highscore) {
		Profile.username = uname;
		Profile.userPassword = pass;
		Profile.highestLevel = curlev;
		Profile.currentScore = curscore;
		Profile.highestScore = highscore;
	}
	
	/**
	 * Sets values of current level, current score and highscore to default values.
	 * @throws FileNotFoundException
	 */
	public void resetProfile() throws FileNotFoundException {
		Profile.setCurScore(0);
		Profile.setLevel(1);
		Profile.setHighestScore(0);
	}
	
	/**
	 * Sets level number to desired number, will usually be used to increment level as user passes it.
	 * @param levelnum - The specified level number being entered.
	 * @throws FileNotFoundException
	 */
	public static void setLevel(int levelnum) throws FileNotFoundException {
		String tempPath = System.getProperty("user.dir") + "\\Profiles\\" + 
				username + ".txt";
		PrintWriter writer = new PrintWriter(tempPath);
		Profile.clearFile();
		writer.print(username + ",");
		writer.print(userPassword + ",");
		Profile.highestLevel = levelnum;
		writer.print(highestLevel + ","); //updates to whatever levelnum is
		writer.print(currentScore + ",");
		writer.print(highestScore + ",");
		writer.close();
	}
	
	/**
	 * Sets current score to desired number.
	 * @param cScore - The specified current score being entered.
	 * @throws FileNotFoundException
	 */
	public static void setCurScore(int cScore) throws FileNotFoundException {
		String tempPath = System.getProperty("user.dir") + "\\Profiles\\" + 
				username + ".txt";
		PrintWriter writer = new PrintWriter(tempPath);
		Profile.clearFile();
		writer.print(username + ",");
		writer.print(userPassword + ",");
		writer.print(highestLevel + ","); 
		Profile.currentScore = cScore;
		writer.print(currentScore + ",");//updates to whatever cscore is
		writer.print(highestScore + ",");
		writer.close();
	}
	
	/**
	 * Sets highscore to desired number.
	 * @param hScore - The specified highlevel score being entered.
	 * @throws FileNotFoundException
	 */
	public static void setHighestScore(int hScore) throws FileNotFoundException {
		String tempPath = System.getProperty("user.dir") + "\\Profiles\\" + 
				username + ".txt";
		PrintWriter writer = new PrintWriter(tempPath);
		Profile.clearFile();
		writer.print(username + ",");
		writer.print(userPassword + ",");
		writer.print(highestLevel + ","); 
		writer.print(currentScore + ",");
		Profile.highestScore = hScore;
		writer.print(highestScore + ","); //updates to whatever hscore is
		writer.close();
	}
	
	public String getUser() {
		return username;
	}
	
	public int getLevel() {
		return highestLevel;
	}
	
	public int getCurrentScore() {
		return currentScore;
	}
	
	public int getHighestScore() {
		return highestScore;
	}
	
	/**
	 * Writes to a file the information required to create a profile - with default values.
	 * @param name - Username the user entered
	 * @param password - Password the user has chosen
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void makeNewProfile(String name, String password) throws FileNotFoundException, UnsupportedEncodingException {
		String tempPath = System.getProperty("user.dir") + "\\Profiles\\" + 
				name + ".txt";
		PrintWriter writer = new PrintWriter(tempPath);
		writer.print(name + ","); 
		writer.print(password + ",");
		writer.print("1" + ","); //Current level
		writer.print("0" + ","); //Current score
		writer.print("0"); //Highestscore
		writer.close();
	}
	
	/**
	 * Once a user is created, an instance of that profile is temporarily created so it can be updated appropriately. 
	 * @param username - The username of the player currently logged in
	 * @return returns the profile of the player currently logged in
	 */
	public static Profile currentProfileInUse(String username) {
		String filename = System.getProperty("user.dir") + "\\Profiles\\" + 
				username + ".txt";
		File userFile = new File(filename);
		try {
			Scanner reader = new Scanner(userFile);
			reader.useDelimiter(",");
			String user = reader.next();
			String pass = reader.next();
			int currentLev = reader.nextInt();
			int currentScore = reader.nextInt();
			int highScore = reader.nextInt();
			Profile currentUser = new Profile(user, pass, currentLev, currentScore,
					highScore);
			reader.close();
			return currentUser;
		} catch (FileNotFoundException e) {
			Alert noUser = new Alert(AlertType.ERROR);
			noUser.setHeaderText("User does not exist!");
			noUser.setContentText("Username cannot be found, try again! If"
				+ " you don't have an account, please create one to play!");
			noUser.showAndWait();
			return null;
		}
		
	}
	
	/**
	 * This method completely clears the users file of all information, ready for it to be re-writen with another method.
	 */
	public static void clearFile() {
		String filename = System.getProperty("user.dir") + "\\Profiles\\" + 
				username + ".txt";
		try {
			FileWriter clear1 = new FileWriter(filename, false);
			PrintWriter clear2 = new PrintWriter(clear1, false);
			clear2.flush();
			clear2.close();
			clear1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
