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
 *@version 1.1
 */
public class Profile {
	private static String username;
	private static String userPassword;
	private static int highestLevel;
	private static int currentScore;
	private static int highestScoreL1; //L means level, number indicates level number.
	private static int highestScoreL2;
	private static int highestScoreL3;
	private static int highestScoreL4;
	private static int highestScoreL5;
	private static int highestScoreL6;
	private static int highestScoreL7;
	private static int highestScoreL8;
	private static int highestScoreL9;
	private static int highestScoreL10;
	
	/**
	 * Creates a profile of the player that has logged in.
	 * @param uname - Username
	 * @param pass - Password
	 * @param curlev - Current level of player
	 * @param curscore - Current score of the player
	 * @param highscore - Current highscore
	 */
	public Profile(String uname, String pass, int curlev, int curscore, int hs1, int hs2, 
			int hs3, int hs4, int hs5, int hs6, int hs7, int hs8, int hs9, int hs10) {
		Profile.username = uname;
		Profile.userPassword = pass;
		Profile.highestLevel = curlev;
		Profile.currentScore = curscore;
		Profile.highestScoreL1 = hs1;
		Profile.highestScoreL2 = hs2;
		Profile.highestScoreL3 = hs3;
		Profile.highestScoreL4 = hs4;
		Profile.highestScoreL5 = hs5;
		Profile.highestScoreL6 = hs6;
		Profile.highestScoreL7 = hs7;
		Profile.highestScoreL8 = hs8;
		Profile.highestScoreL9 = hs9;
		Profile.highestScoreL10 = hs10;
	}
	
	/**
	 * Sets values of current level, current score and highscore to default values.
	 * @throws FileNotFoundException
	 */
	public static void resetProfile() throws FileNotFoundException {
		Profile.setCurScore(0);
		Profile.setLevel(1);
		Profile.resetHS();
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
		writer.print(highestScoreL1 + ",");
		writer.print(highestScoreL2 + ",");
		writer.print(highestScoreL3 + ",");
		writer.print(highestScoreL4 + ",");
		writer.print(highestScoreL5 + ",");
		writer.print(highestScoreL6 + ",");
		writer.print(highestScoreL7 + ",");
		writer.print(highestScoreL8 + ",");
		writer.print(highestScoreL9 + ",");
		writer.print(highestScoreL10);
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
		writer.print(highestScoreL1 + ",");
		writer.print(highestScoreL2 + ",");
		writer.print(highestScoreL3 + ",");
		writer.print(highestScoreL4 + ",");
		writer.print(highestScoreL5 + ",");
		writer.print(highestScoreL6 + ",");
		writer.print(highestScoreL7 + ",");
		writer.print(highestScoreL8 + ",");
		writer.print(highestScoreL9 + ",");
		writer.print(highestScoreL10);
		writer.close();
	}
	
	/**
	 * Sets a particular levels highscore to particular value.
	 * @param hScore - The specified highlevel score being entered.
	 * @throws FileNotFoundException
	 */
	public static void setHighscoreToValue(int hScore, int value) throws FileNotFoundException {
		String tempPath = System.getProperty("user.dir") + "\\Profiles\\" + 
				username + ".txt";
		PrintWriter writer = new PrintWriter(tempPath);
		Profile.clearFile();
		writer.print(username + ",");
		writer.print(userPassword + ",");
		writer.print(highestLevel + ","); 
		writer.print(currentScore + ",");
		if(hScore == 1) {
			Profile.highestScoreL1 = value;
			writer.print(highestScoreL1 + ",");
			writer.print(highestScoreL2 + ",");
			writer.print(highestScoreL3 + ",");
			writer.print(highestScoreL4 + ",");
			writer.print(highestScoreL5 + ",");
			writer.print(highestScoreL6 + ",");
			writer.print(highestScoreL7 + ",");
			writer.print(highestScoreL8 + ",");
			writer.print(highestScoreL9 + ",");
			writer.print(highestScoreL10);
		} else if(hScore == 2) {
			writer.print(highestScoreL1 + ",");
			Profile.highestScoreL2 = value;
			writer.print(highestScoreL2 + ",");
			writer.print(highestScoreL3 + ",");
			writer.print(highestScoreL4 + ",");
			writer.print(highestScoreL5 + ",");
			writer.print(highestScoreL6 + ",");
			writer.print(highestScoreL7 + ",");
			writer.print(highestScoreL8 + ",");
			writer.print(highestScoreL9 + ",");
			writer.print(highestScoreL10);
		} else if(hScore == 3) {
			writer.print(highestScoreL1 + ",");
			writer.print(highestScoreL2 + ",");
			Profile.highestScoreL3 = value;
			writer.print(highestScoreL3 + ",");
			writer.print(highestScoreL4 + ",");
			writer.print(highestScoreL5 + ",");
			writer.print(highestScoreL6 + ",");
			writer.print(highestScoreL7 + ",");
			writer.print(highestScoreL8 + ",");
			writer.print(highestScoreL9 + ",");
			writer.print(highestScoreL10);
		} else if(hScore == 4) {
			writer.print(highestScoreL1 + ",");
			writer.print(highestScoreL2 + ",");
			writer.print(highestScoreL3 + ",");
			Profile.highestScoreL4 = value;
			writer.print(highestScoreL4 + ",");
			writer.print(highestScoreL5 + ",");
			writer.print(highestScoreL6 + ",");
			writer.print(highestScoreL7 + ",");
			writer.print(highestScoreL8 + ",");
			writer.print(highestScoreL9 + ",");
			writer.print(highestScoreL10);
		} else if(hScore == 5) {
			writer.print(highestScoreL1 + ",");
			writer.print(highestScoreL2 + ",");
			writer.print(highestScoreL3 + ",");
			writer.print(highestScoreL4 + ",");
			Profile.highestScoreL5 = value;
			writer.print(highestScoreL5 + ",");
			writer.print(highestScoreL6 + ",");
			writer.print(highestScoreL7 + ",");
			writer.print(highestScoreL8 + ",");
			writer.print(highestScoreL9 + ",");
			writer.print(highestScoreL10);
		} else if(hScore == 6) {
			writer.print(highestScoreL1 + ",");
			writer.print(highestScoreL2 + ",");
			writer.print(highestScoreL3 + ",");
			writer.print(highestScoreL4 + ",");
			writer.print(highestScoreL5 + ",");
			Profile.highestScoreL6 = value;
			writer.print(highestScoreL6 + ",");
			writer.print(highestScoreL7 + ",");
			writer.print(highestScoreL8 + ",");
			writer.print(highestScoreL9 + ",");
			writer.print(highestScoreL10);
		} else if(hScore == 7) {
			writer.print(highestScoreL1 + ",");
			writer.print(highestScoreL2 + ",");
			writer.print(highestScoreL3 + ",");
			writer.print(highestScoreL4 + ",");
			writer.print(highestScoreL5 + ",");
			writer.print(highestScoreL6 + ",");
			Profile.highestScoreL7 = value;
			writer.print(highestScoreL7 + ",");
			writer.print(highestScoreL8 + ",");
			writer.print(highestScoreL9 + ",");
			writer.print(highestScoreL10);
		} else if(hScore == 8) {
			writer.print(highestScoreL1 + ",");
			writer.print(highestScoreL2 + ",");
			writer.print(highestScoreL3 + ",");
			writer.print(highestScoreL4 + ",");
			writer.print(highestScoreL5 + ",");
			writer.print(highestScoreL6 + ",");
			writer.print(highestScoreL7 + ",");
			Profile.highestScoreL8 = value;
			writer.print(highestScoreL8 + ",");
			writer.print(highestScoreL9 + ",");
			writer.print(highestScoreL10);
		} else if(hScore == 9) {
			writer.print(highestScoreL1 + ",");
			writer.print(highestScoreL2 + ",");
			writer.print(highestScoreL3 + ",");
			writer.print(highestScoreL4 + ",");
			writer.print(highestScoreL5 + ",");
			writer.print(highestScoreL6 + ",");
			writer.print(highestScoreL7 + ",");
			writer.print(highestScoreL8 + ",");
			Profile.highestScoreL9 = value;
			writer.print(highestScoreL9 + ",");
			writer.print(highestScoreL10);
		} else if(hScore == 10) {
			writer.print(highestScoreL1 + ",");
			writer.print(highestScoreL2 + ",");
			writer.print(highestScoreL3 + ",");
			writer.print(highestScoreL4 + ",");
			writer.print(highestScoreL5 + ",");
			writer.print(highestScoreL6 + ",");
			writer.print(highestScoreL7 + ",");
			writer.print(highestScoreL8 + ",");
			writer.print(highestScoreL9 + ",");
			Profile.highestScoreL10 = value;
			writer.print(highestScoreL10);
		} else {
			//Should never run because this is called by code with validation, just neeeded else case.
			System.out.println("There's been an error!");
		}
		writer.close();
	}
	
	public static void resetHS() throws FileNotFoundException {
		String tempPath = System.getProperty("user.dir") + "\\Profiles\\" + 
				username + ".txt";
		PrintWriter writer = new PrintWriter(tempPath);
		Profile.clearFile();
		writer.print(username + ",");
		writer.print(userPassword + ",");
		writer.print(highestLevel + ","); 
		writer.print(currentScore + ",");
		Profile.highestScoreL1 = 0;
		writer.print(highestScoreL1 + ",");
		Profile.highestScoreL2 = 0;
		writer.print(highestScoreL2 + ",");
		Profile.highestScoreL3 = 0;
		writer.print(highestScoreL3 + ",");
		Profile.highestScoreL4 = 0;
		writer.print(highestScoreL4 + ",");
		Profile.highestScoreL5 = 0;
		writer.print(highestScoreL5 + ",");
		Profile.highestScoreL6 = 0;
		writer.print(highestScoreL6 + ",");
		Profile.highestScoreL7 = 0;
		writer.print(highestScoreL7 + ",");
		Profile.highestScoreL8 = 0;
		writer.print(highestScoreL8 + ",");
		Profile.highestScoreL9 = 0;
		writer.print(highestScoreL9 + ",");
		Profile.highestScoreL10 = 0;
		writer.print(highestScoreL10);
		writer.close();
	}
	
	public static String getUser() {
		return username;
	}
	
	public static int getLevel() {
		return highestLevel;
	}
	
	public int getCurrentScore() {
		return currentScore;
	}
	
	public static int getHighestScoreL1() {
		return highestScoreL1;
	}
	
	public static int getHighestScoreL2() {
		return highestScoreL2;
	}
	public static int getHighestScoreL3() {
		return highestScoreL3;
	}
	public static int getHighestScoreL4() {
		return highestScoreL4;
	}
	public static int getHighestScoreL5() {
		return highestScoreL5;
	}
	public static int getHighestScoreL6() {
		return highestScoreL6;
	}
	public static int getHighestScoreL7() {
		return highestScoreL7;
	}
	public static int getHighestScoreL8() {
		return highestScoreL8;
	}
	public static int getHighestScoreL9() {
		return highestScoreL9;
	}
	public static int getHighestScoreL10() {
		return highestScoreL10;
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
		writer.print("0" + ","); //HighestscoreL1
		writer.print("0" + ","); //HighestscoreL2
		writer.print("0" + ","); //HighestscoreL3
		writer.print("0" + ","); //HighestscoreL4
		writer.print("0" + ","); //HighestscoreL5
		writer.print("0" + ","); //HighestscoreL6
		writer.print("0" + ","); //HighestscoreL7
		writer.print("0" + ","); //HighestscoreL8
		writer.print("0" + ","); //HighestscoreL9
		writer.print("0"); //HighestscoreL10
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
			int highScoreL1 = reader.nextInt();
			int highScoreL2 = reader.nextInt();
			int highScoreL3 = reader.nextInt();
			int highScoreL4 = reader.nextInt();
			int highScoreL5 = reader.nextInt();
			int highScoreL6 = reader.nextInt();
			int highScoreL7 = reader.nextInt();
			int highScoreL8 = reader.nextInt();
			int highScoreL9 = reader.nextInt();
			int highScoreL10 = reader.nextInt();
			Profile currentUser = new Profile(user, pass, currentLev, currentScore,
					highScoreL1, highScoreL2, highScoreL3, highScoreL4, highScoreL5, highScoreL6, highScoreL7, 
					highScoreL8, highScoreL9,highScoreL10);
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