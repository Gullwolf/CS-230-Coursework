import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is the main menu for loading games, where you can select
 * the level number you want, and play it (depending if you've reached that level).
 * @author Cai Sidaway
 * @version 1
 */
public class LoadGameMain extends Application {

	public static String currUser;
	
	private static Pane root;
	
	/**
	 * Lauches the application.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}	
	
	/**
	 * Main method displays the main menu.
	 */
	public void start(Stage loadMainStage) throws Exception {
		
		//Retreives the profile that's currently logged in
		Profile player = Profile.currentProfileInUse(currUser); 
		
		final int CANVAS_WIDTH = 900;
		final int CANVAS_HEIGHT = 700;
	
		loadMainStage.setTitle("Choose a level!");
		Group root = new Group();
		
		Scene mainScene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
		loadMainStage.setScene(mainScene);
	
		
		//All labels
		Label availGames = new Label("All levels:");
		availGames.setFont(new Font("Times new roman", 30));
		availGames.setLayoutX(40);
		availGames.setLayoutY(10);
		root.getChildren().add(availGames);
		
		
		//Listview allowing selectable games
		ListView<String> selectGame = new ListView<>();
		selectGame.getItems().setAll("Level 1                                   Highscore:" + "    "  + Profile.getHighestScoreL1(), 
				"Level 2                                   Highscore: " + "    "  + Profile.getHighestScoreL2(),
				"Level 3                                   Highscore: " + "    "  + Profile.getHighestScoreL3(),
				"Level 4                          	      Highscore: "  + "    "  + Profile.getHighestScoreL4(),
				"Level 5                          	      Highscore: "  + "    "  + Profile.getHighestScoreL5(),
				"Level 6                          	      Highscore: "  + "    "  + Profile.getHighestScoreL6(),
				"Level 7                          	      Highscore: "  + "    "  + Profile.getHighestScoreL7(),
				"Level 8                           	      Highscore: "  + "    "  + Profile.getHighestScoreL8(),
				"Level 9                           	      Highscore: "  + "    "  + Profile.getHighestScoreL9(),
				"Level 10                                 Highscore: " + "    "  + Profile.getHighestScoreL10());
		selectGame.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		selectGame.setPrefSize(820, 420);
		selectGame.setStyle("-fx-font-size: 2.0em ;");
		selectGame.setLayoutX(40);
		selectGame.setLayoutY(50);
		root.getChildren().add(selectGame);
		
		//Buttons
		Button leaderboardBut = new Button("Leaderboard");
		leaderboardBut.setLayoutX(350);
		leaderboardBut.setLayoutY(540);
		leaderboardBut.setPrefSize(200, 100);
		leaderboardBut.addEventHandler(ActionEvent.ACTION, (e) -> {
			Stage load = new Stage();
			try {
				loadMainStage.hide();
				new LeaderBoardMain().start(load);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		root.getChildren().add(leaderboardBut);
		
		Button logout = new Button("Logout");
		logout.setLayoutX(650);
		logout.setLayoutY(540);
		logout.setPrefSize(200, 100);
		logout.addEventHandler(ActionEvent.ACTION, (e) -> {
			Stage load = new Stage();
			try {
				loadMainStage.hide();
				new MainMenu().start(load);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
		root.getChildren().add(logout);
		
		ButtonType ok = new ButtonType("Ok");
		ButtonType cancel = new ButtonType("Cancel");
		ButtonType restart = new ButtonType("Restart");
		ButtonType cont = new ButtonType("Continue");
		Button play = new Button("Play selected level!");
		play.setLayoutX(50);
		play.setLayoutY(540);
		play.setPrefSize(200, 100);
		play.addEventHandler(ActionEvent.ACTION, (e) -> {
		int selected = selectGame.getSelectionModel().getSelectedIndex();
		SaveGame.setCurrentLevel(selected - 1);
		if (selected < player.getLevel() - 1) { //If the index selected is less than the level the player is on, then they can play it
			Alert playedBefore = new Alert(AlertType.CONFIRMATION, "Want to continue?", ok,cancel);
			playedBefore.setTitle("You've played this before!");
			playedBefore.setHeaderText("This level has been previously completed");
			playedBefore.setContentText("If you'd like to retry this level, click ok " +
					" if you want to leave your current high score, click cancel");
			playedBefore.showAndWait().ifPresent(response -> {
				if (response == ok) {
					if (response == ok) {
						SaveGame.setCurrentLevel(selected - 1);
						Stage load = new Stage();
						try {
							loadMainStage.hide();
							TrainCanvas.setCurrentLevel(selected + 1);
							new TrainCanvas().start(load);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else if (response == cancel) {
						playedBefore.close();
					}
					}
			});
			
		} else if (selected == player.getLevel() - 1) { //If this is the current level the player is playing
			if (player.getCurrentScore() != 0) {
			Alert currentlyPlaying = new Alert(AlertType.CONFIRMATION, "Want to play?", cont, restart, cancel);
			currentlyPlaying.setTitle("You're currently playing this level");
			currentlyPlaying.setHeaderText("Your current score: " + player.getCurrentScore());
			currentlyPlaying.setContentText("Would you like to continue with your previous game, or restart the level?");
			currentlyPlaying.showAndWait().ifPresent(response -> {
				if (response == cont) { //Instance where you need to load saved game file. 
					SaveGame.setCurrentLevel(selected - 1);
					Stage load = new Stage();
					int forPath = selected + 1;
					String path = System.getProperty("user.dir") + "\\SaveGame\\" + 
							"Level" + forPath + "_" + currUser +  ".txt";
					try {
						loadMainStage.hide();
						TrainCanvas.setIsSavedGame(true);
						TrainCanvas.setLoadFilePath(path);
						new TrainCanvas().start(load);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (response == cancel) { //Instance of cancellation
					currentlyPlaying.close();
				} else { //Instance of restart (loading a game like normal)
					Stage load = new Stage();
					try {
						loadMainStage.hide();
						TrainCanvas.setCurrentLevel(selected + 1);
						new TrainCanvas().start(load);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} 
					
			});
		} else {
			Alert currentlyPlaying = new Alert(AlertType.CONFIRMATION, "Goodluck!", ok, cancel);
			currentlyPlaying.setTitle("This is your first attempt at this round, goodluck!");
			currentlyPlaying.setHeaderText("Your current score: " + player.getCurrentScore());
			currentlyPlaying.setContentText("Press 'ok' to play and 'cancel' to go back");
			currentlyPlaying.showAndWait().ifPresent(response -> {
				if (response == ok) { //Instance where it's players first time playing this level
					SaveGame.setCurrentLevel(selected - 1);
					Stage load = new Stage();
					try {
						loadMainStage.hide();
						TrainCanvas.setCurrentLevel(selected + 1);
						new TrainCanvas().start(load);
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
				} else if (response == cancel) { //Instance of cancellation
					currentlyPlaying.close();
				}
			});
		}
		} else { //User is unable to play levels that they've not worked up to, will receieve error message. 

			Alert notAble = new Alert(AlertType.ERROR);
			notAble.setTitle("You can't play this!");
			notAble.setHeaderText("Sorry!");
			notAble.setContentText("You have't completed level " + player.getLevel() + 
					", to continue to this level, beat the levels leading to this one!");
			notAble.showAndWait();
		}
		
		});
		root.getChildren().add(play);

		loadMainStage.show();
		
	}
	
	/**
	 * Gets parsed the name of the person that's logged into the system,
	 * so it can load specific levels.
	 * @param person The name of the person playing
	 */
	public static void user(String person) {
		currUser = person;
	}
	

}