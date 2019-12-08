import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class calculates the leaderboard of a level and stores it in an
 * appropriate file.
 * 
 * @author Sindiso Sibanda
 * @version 1.0
 */
public class LeaderBoard {

	// "highestScores" and "highestPlayers" are set to default values incase
	// leaderboards are partially full
	private int[] highestScores = {0, 0, 0};

	private String[] highestPlayers = {"null", "null", "null"};

	private int[] newScores = new int[3];

	private String[] newPlayers = new String[3];

	private int level;

	/**
	 * Compares a player's score to a level's leaderboard scores and updates it if
	 * needed.
	 * 
	 * @param player    - the name of the current player
	 * @param highScore - the player's achieved score for the level
	 * @param level     - the current level the player has completed
	 * @throws FileNotFoundException
	 */
	public LeaderBoard(String player, int highScore, int level) throws FileNotFoundException {
		this.level = level;
		getLeaderBoard(level);
		updateLeaderBoard(player, highScore);
		writeToLeaderBoard();
	}

	/**
	 * Gets the leaderboard for a specific level.
	 * 
	 * @param level - the level of interest for the leaderboard
	 */
	public LeaderBoard(int level) {
		getLeaderBoard(level);
	}

	/**
	 * Reads the file containing the leaderboard and stores player names and scores.
	 * 
	 * @param level - the level whose leaderboard needs to be read
	 */
	private void getLeaderBoard(int level) {
		//important!! this needs to be changed, was unaware of how the files were stored or saved
		String fileName = "LeaderBoardForLevel" + level + ".txt";
		File leaderBoard = new File(fileName);
		try {
			Scanner reader = new Scanner(leaderBoard);
			int position = 0;
			// iterates through the leaderboard to get data
			while (reader.hasNext()) {
				Scanner scoreInfo = new Scanner(reader.nextLine());
				scoreInfo.useDelimiter(",");
				highestPlayers[position] = scoreInfo.next();
				highestScores[position] = scoreInfo.nextInt();
				position++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Specified file cannot be found. Exiting program");
			System.exit(1);
		}
	}

	/**
	 * updates the leaderboard to a new set of arrays. If player's score isn't high
	 * enough then new leaderboard should be identical
	 * 
	 * @param player - the current player's name
	 * @param score  - the current player's score
	 */
	private void updateLeaderBoard(String player, int score) {
		int insertionRank = findNewRank(score);
		int offSet = 0; // this is used to change the position of the players in the new array
		for (int rank = 0; rank < highestScores.length; rank++) {
			if (rank == insertionRank) {
				newPlayers[rank] = player;
				newScores[rank] = score;
				offSet++; // an offset of 1 would push the next players one position down
			} else {
				newPlayers[rank] = highestPlayers[rank - offSet];
				newScores[rank] = highestScores[rank - offSet];
			}
		}
	}

	/**
	 * Compares the player's score to the scores in the leaderboard to find the
	 * player's position in the highscores.
	 * 
	 * @param highScore - the player's achieved score
	 * @return the position in the new array of highscores the player's score
	 *         belongs in
	 */
	@SuppressWarnings("checkstyle:WhitespaceAfter")
	private int findNewRank(int highScore) {
		int newRank = -1; // default value for player rank, if unchanged leaderboard will not be affected
		for (int rank = 0; rank < highestScores.length; rank++) {
			if (highScore > highestScores[rank]) {
				newRank = rank;
				return newRank;
			}
		}
		return newRank;
	}

	/**
	 * overwrites the relevant leaderboard file with the new checked/up-to-date
	 * leaderboard.
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("checkstyle:JavadocMethod")
	private void writeToLeaderBoard() throws FileNotFoundException {
		//important!! this needs to be changed, was unaware of how the files were stored or saved
		String fileName = "LeaderBoardForLevel" + level + ".txt";
		PrintWriter info = new PrintWriter(fileName);
		clearLeaderBoard(fileName);
		for (int i = 0; i < 3; i++) {
			info.println(newPlayers[i] + "," + newScores[i]);
		}
		info.close();
	}

	/**
	 * clears the appropriate leaderboard file of all information to be overwritten.
	 * 
	 * @param fileToClear - the file path of the leaderboard file to be cleared
	 */
	private void clearLeaderBoard(String fileToClear) {
		try {
			FileWriter clear1 = new FileWriter(fileToClear, false);
			PrintWriter clear2 = new PrintWriter(clear1, false);
			clear2.flush();
			clear2.close();
			clear1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
