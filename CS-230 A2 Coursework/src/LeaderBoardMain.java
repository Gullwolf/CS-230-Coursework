import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Implements leaderboard which can be updated via combobox.
 * @author Cai Sidaway, Sindiso Sibanda
 * @version 1.0
 */
public class LeaderBoardMain extends Application {
	private static int index = 0;

	public static void main(String[] args) {
		launch(args);

	}	


	/**
	 * Loads canvas and displays inital level 1 highscores.
	 */
	@Override
	public void start(Stage main) throws Exception {
		final int CANVAS_HEIGHT = 700;
		final int CANVAS_WIDTH = 900;

		main.setTitle("Leaderboards");
		Group root = new Group();

		Scene mainScene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
		main.setResizable(false);

		//Labels
		Label choose = new Label("Please choose a level you'd like to see highscores for:");
		choose.setFont(new Font("Times new roman", 30));
		choose.setLayoutX(40);
		choose.setLayoutY(50);
		root.getChildren().add(choose);

		//Button
		Button back = new Button("Back");
		back.setPrefSize(500, 60);
		back.setTranslateX(200);
		back.setTranslateY(600);
		back.addEventHandler(ActionEvent.ACTION, (e) -> {
			Stage load = new Stage();
			try {
				main.hide();
				new LoadGameMain().start(load);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		root.getChildren().add(back);

		//ListView
		ListView<String> players = new ListView<>();
		players.setPrefSize(820, 420);
		players.setStyle("-fx-font-size: 2.0em ;");
		players.setLayoutX(40);
		players.setLayoutY(150);
		root.getChildren().add(players);
		players.getItems().add("Position                                            Score                                       Player");

		//Combobox
		String[] availableLevels = {"Level 1", "Level 2", "Level 3",
				"Level 4", "Level 5", "Level 6", "Level 7", "Level 8",
				 "Level 9", "Level 10"};
		ComboBox select = new ComboBox(FXCollections.observableArrayList(availableLevels));
		select.getSelectionModel().selectFirst();
		root.getChildren().add(select);
		select.setPrefSize(150, 50);
		select.setLayoutX(700);
		select.setLayoutY(50);
		select.setStyle("-fx-font-size: 2em; ");
		select.addEventHandler(ActionEvent.ACTION, (e) -> {
			int index = LeaderBoardMain.getIndex(select.getSelectionModel().getSelectedIndex());
			try {
				players.getItems().clear();
				players.getItems().add("Position                                            Score                                       Player");
				LeaderBoardMain.refreshBoard(index, players);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		});
		 //This intially loads the listview with highscores from level1
		int position = 1;
		File directory = new File(System.getProperty("user.dir") + "\\Profiles\\");
		Scanner scanFile = null;
		ArrayList<String> listOfScores = new ArrayList<String>();
		for (File file : directory.listFiles()) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			}
		ArrayList<String> finalList = LeaderBoardMain.sort(listOfScores);
		int count = 1;
		for (int i = 0; i < finalList.size(); i++) {
			String pos = Integer.toString(position);
			String score = finalList.get(i);
			char first = score.charAt(0);
			int scoreNum = Character.getNumericValue(first);
			String name = score.substring(1); 
			if (scoreNum != 0) {
				players.getItems().add(pos + "                                                           " + 
						Integer.toString(scoreNum) + "                                           " + name);
						position++;
						count++;
			} else if (scoreNum == 0 && count == 1) {
				players.getItems().add("Nobody has set a highscore for this level yet, be the first!");
				count++;
			}
		}
		main.setScene(mainScene);
		main.show();	
	}

	/**
	 * Translates index selection from combobox to index numbers from profile text files.
	 * @param indexSelected The initally selected index
	 * @return The index in which the level highscore can be found in text file
	 */
	private static int getIndex(int indexSelected) {
		int returning = 0;
		switch (indexSelected) {
		case 0:
			returning = 4;
			break;
		case 1:
			returning = 5;
			break;
		case 2:
			returning = 6;
			break;
		case 3:
			returning = 7;
			break;
		case 4:
			returning = 8;
			break;
		case 5:
			returning = 9;
			break;
		case 6:
			returning = 10;
			break;
		case 7:
			returning = 11;
			break;
		case 8:
			returning = 12;
			break;
		case 9:
			returning = 13;
			break;
		default:
			System.out.println("Theres been an issue");
		}
		return returning;
	}

	/**
	 * Sorts arraylist of scores and names into decending order.
	 * @param list The inital, unsorted arraylist.
	 * @return A sorted arraylist in decending values.
	 */
	private static ArrayList<String> sort(ArrayList<String> list) {
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}

	/**
	 * When user chooses level this will refresh to the selected level.
	 * @param index The index of the combobox selected by user, which has been translated into index for text file
	 * @param players The listview item itself
	 * @throws FileNotFoundException
	 */
	private static void refreshBoard(int index, ListView<String> players) throws FileNotFoundException {
		int position = 1;
		File directory = new File(System.getProperty("user.dir") + "\\Profiles\\");
		Scanner scanFile = null;
		ArrayList<String> listOfScores = new ArrayList<String>();
		//Finds which level the user wants to see and populates the listview accordingly
		for (File file : directory.listFiles()) {
			if (index == 4) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			} else if (index == 5) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			} else if (index == 6) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			} else if (index == 7) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			} else if (index == 8) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			} else if (index == 9) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			} else if (index == 10) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			} else if (index == 11) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			} else if (index == 12) {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			} else {
				scanFile = new Scanner(file);
				scanFile.useDelimiter(",");
				String name = scanFile.next();
				scanFile.next();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				scanFile.nextInt();
				int score = scanFile.nextInt();
				String temp = Integer.toString(score);
				listOfScores.add(temp + name);
			}

		}

		ArrayList<String> finalList = LeaderBoardMain.sort(listOfScores);
		int count = 1;
		for (int i = 0; i < finalList.size(); i++) {
			String pos = Integer.toString(position);
			String score = finalList.get(i);
			char first = score.charAt(0);
			int scoreNum = Character.getNumericValue(first);
			String name = score.substring(1); 
			if (scoreNum != 0) {
				players.getItems().add(pos + "                                                           " + 
						Integer.toString(scoreNum) + "                                           " + name);
						position++;
						count++;
			} else if (scoreNum == 0 && count == 1) {
				players.getItems().add("Nobody has set a highscore for this level yet, be the first!");
				count++;
			}
		}
	}
}