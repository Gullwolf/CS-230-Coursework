import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.Timer;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class displays the main menu screen.
 * @author Cai Sidaway
 * @version 1.0
 */
public class MainMenu extends Application {
	Scene sceneLogin;
	Scene createAccount;
	static String[] arguments;
	
	/**
	 * Main method.
	 * @param args Set of arguments
	 */
	public static void main(String[] args) {
		launch(args);
		arguments = args;
	}
	
	
	/**
	 * This method displays the main menu.
	 */
	@Override
	public void start(Stage mainStage) throws Exception {
		final int CANVAS_HEIGHT = 700;
		final int CANVAS_WIDTH = 900;
		
		final int LOGINSCREEN_HEIGHT = 600;
		final int LOGINSCREEN_WIDTH = 600;
		
		//Creates background image
		mainStage.setTitle("Trains Challenge");
		Image background = new Image("file:Art/Background.png");
		ImageView screenBackground = new ImageView(background);
		
		Group root = new Group();
		root.getChildren().add(screenBackground);
		
		//Main page scene
		Scene mainScene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
		mainStage.setResizable(false);
	
		//Buttons 

		
		Button buttonCABack = new Button("Back");
		buttonCABack.setPrefSize(200, 60);
		buttonCABack.setTranslateX(150);
		buttonCABack.setTranslateY(260);
		buttonCABack.setOnAction(e -> mainStage.setScene(sceneLogin));
	
		
		Button buttonSignUp = new Button("Sign up!");
		buttonSignUp.setPrefSize(120, 60);
		buttonSignUp.setTranslateX(0);
		buttonSignUp.setTranslateY(230);
		buttonSignUp.setOnAction(e -> mainStage.setScene(createAccount));
	

		
		//All labels for login scene, main and create account
		Label loginLabel = new Label("Please sign in");
		loginLabel.setTranslateY(-270);
		loginLabel.setTranslateX(-200);
		loginLabel.setFont(new Font(30));
		
		Label saysLoginLabel = new Label("Username:");
		saysLoginLabel.setTranslateY(-150);
		saysLoginLabel.setTranslateX(-220);
		saysLoginLabel.setFont(new Font(30));
		
		Label passwordLabel = new Label("Password:");
		passwordLabel.setTranslateY(-50);
		passwordLabel.setTranslateX(-225);
		passwordLabel.setFont(new Font(30));
		
		Label noSignupLabel = new Label("Haven't played before? Sign up!");
		noSignupLabel.setTranslateY(168);
		noSignupLabel.setTranslateX(-5);
		noSignupLabel.setFont(new Font(20));
		
		Label saysLoginLabelCA = new Label("Select username:");
		saysLoginLabelCA.setTranslateY(-150);
		saysLoginLabelCA.setTranslateX(-200);
		saysLoginLabelCA.setFont(new Font(24));
		
		Label passwordLabelCA = new Label("Enter password:");
		passwordLabelCA.setTranslateY(-50);
		passwordLabelCA.setTranslateX(-205);
		passwordLabelCA.setFont(new Font(24));
		
		Label confPasswordLabelCA = new Label("Confirm password:");
		confPasswordLabelCA.setTranslateY(50);
		confPasswordLabelCA.setTranslateX(-190);
		confPasswordLabelCA.setFont(new Font(24));
		
		Label createAccountLabel = new Label("Create an account to start playing!");
		createAccountLabel.setTranslateY(-270);
		createAccountLabel.setTranslateX(-98);
		createAccountLabel.setFont(new Font(25));
		
		//User input boxes for login screen and create account
		TextField usernameInput = new TextField();
		usernameInput.setTranslateY(-100);
		usernameInput.setPromptText("Your username");
		usernameInput.setFont(Font.font("Times new Roman",FontWeight.BOLD, 30));
		
		PasswordField password = new PasswordField();
		password.setPromptText("Your password");
		password.setTranslateY(0);
		password.setFont(Font.font("Times new Roman",FontWeight.BOLD, 30));

		TextField chooseUsernameCA = new TextField();
		chooseUsernameCA.setTranslateY(-100);
		chooseUsernameCA.setPromptText("Select a username");
		chooseUsernameCA.setFont(Font.font("Times new Roman",FontWeight.BOLD, 20));
		
		PasswordField choosePasswordCA = new PasswordField();
		choosePasswordCA.setPromptText("Your password");
		choosePasswordCA.setTranslateY(0);
		choosePasswordCA.setFont(Font.font("Times new Roman",FontWeight.BOLD, 20));
		
		PasswordField confirmPasswordCA = new PasswordField();
		confirmPasswordCA.setPromptText("Re-enter password");
		confirmPasswordCA.setTranslateY(100);
		confirmPasswordCA.setFont(Font.font("Times new Roman",FontWeight.BOLD, 20));
		
		Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.getChildren().add(canvas);
		
		//Buttons placed in weird place because they have to be.
		Button buttonCreateAccountCA = new Button("Create account");
		buttonCreateAccountCA.setPrefSize(200, 60);
		buttonCreateAccountCA.setTranslateX(-150);
		buttonCreateAccountCA.setTranslateY(260);
		buttonCreateAccountCA.addEventHandler(ActionEvent.ACTION, (e)-> {
		if ((MainMenu.createAccount(chooseUsernameCA.getText(), choosePasswordCA.getText(), 
				confirmPasswordCA.getText())) == false) {
			chooseUsernameCA.clear();
			choosePasswordCA.clear();
			confirmPasswordCA.clear();
		} else {
			chooseUsernameCA.clear();
			choosePasswordCA.clear();
			confirmPasswordCA.clear();
			mainStage.setScene(mainScene);
		}
		
		});

		Button buttonLoginBack = new Button("Back");
		buttonLoginBack.setPrefSize(200, 60);
		buttonLoginBack.setTranslateX(150);
		buttonLoginBack.setTranslateY(100);
		buttonLoginBack.addEventHandler(ActionEvent.ACTION, (e)->{
		usernameInput.clear();
		password.clear();
		mainStage.setScene(mainScene);
		});
		
		Button buttonLogin = new Button("Login");
		buttonLogin.setPrefSize(200, 60);
		buttonLogin.setTranslateX(-150);
		buttonLogin.setTranslateY(100);
		buttonLogin.addEventHandler(ActionEvent.ACTION, (e)-> {
			if((MainMenu.validateLogin(usernameInput.getText(), password.getText())) == false) {
				usernameInput.clear();
				password.clear();
			} else {
				String tempPerson = usernameInput.getText();
				Profile.currentProfileInUse(usernameInput.getText()); //parses profile the profile that just logged in.
				usernameInput.clear();
				password.clear();
				Stage load = new Stage();
				try {
					mainStage.hide();
					LoadGameMain.user(tempPerson);
					new LoadGameMain().start(load);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		

		//Writes the game title at top of page
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font setFont = Font.font("Times new Roman", 70);
		gc.setFont(setFont);
		gc.fillText("Trains challenge", 240, 100);
		gc.strokeText("Trains challenge", 240, 100);
		
		//Main play game button - Only seems to work when placed here
		Button play = new Button("Play!");
		play.setPrefSize(300, 100);
		play.setStyle("-fx-font-size: 2em; ");
		play.setLayoutX(320);
		play.setLayoutY(250);
		play.setOnAction(e -> mainStage.setScene(sceneLogin));
		root.getChildren().add(play);
		
		ButtonType ok = new ButtonType("Ok");
		ButtonType cancel = new ButtonType("Cancel");
		Button buttonQuitGame = new Button("Quit game!");
		buttonQuitGame.setPrefSize(300, 100);
		buttonQuitGame.setStyle("-fx-font-size: 2em; ");
		buttonQuitGame.setLayoutX(320);
		buttonQuitGame.setLayoutY(375);
		buttonQuitGame.addEventHandler(ActionEvent.ACTION, (e) -> {
			Alert goodbye = new Alert(AlertType.CONFIRMATION, "Hi", ok, cancel);
			goodbye.setTitle("Thanks for playing!");
			goodbye.setHeaderText("Are you sure you wish to quit?");
			goodbye.setContentText("Press 'ok' to quit the game, or 'cancel' to play some more!");
			goodbye.showAndWait().ifPresent(response -> {
					if (response == ok) {
						System.exit(0);
					} else {
						goodbye.close();
					}	
				});

			});
		root.getChildren().add(buttonQuitGame);
		
		//Login screen scene
		StackPane login = new StackPane();
		login.getChildren().addAll(loginLabel, saysLoginLabel, passwordLabel, usernameInput,
				password, buttonLogin, buttonLoginBack, noSignupLabel, buttonSignUp);
		sceneLogin = new Scene(login, LOGINSCREEN_WIDTH, LOGINSCREEN_HEIGHT);
		
		//Create account scene
		StackPane createAccountPane = new StackPane();
		createAccountPane.getChildren().addAll(saysLoginLabelCA, passwordLabelCA, buttonCABack, createAccountLabel,
				confPasswordLabelCA, chooseUsernameCA, choosePasswordCA, confirmPasswordCA, buttonCreateAccountCA);
		createAccount = new Scene(createAccountPane, LOGINSCREEN_WIDTH, LOGINSCREEN_HEIGHT);
		
		//Creates 'pulsating' and moving message of day
		Text messageDay = new Text();
		messageDay.setX(10);
		messageDay.setY(200);
		messageDay.setText(DailyMessage.getPuzzle());
		messageDay.setFont(new Font(20));
		FadeTransition ftransition = new FadeTransition(Duration.seconds(2.0), messageDay);
		ftransition.setFromValue(1.0);
		ftransition.setToValue(0.2); //ftransition controls pulsating
		ftransition.setCycleCount(Animation.INDEFINITE);
		ftransition.play();
		double sceneWidth = mainScene.getWidth();
		//Code below controls the movement of the label
		KeyValue initalKey = new KeyValue(messageDay.translateXProperty(), sceneWidth);
		KeyValue endKey = new KeyValue(messageDay.translateXProperty(),-1.0 * sceneWidth);
		KeyFrame startFrame = new KeyFrame(Duration.ZERO, initalKey);
		KeyFrame endFrame = new KeyFrame(Duration.seconds(6), endKey);
		Timeline timeline = new Timeline(startFrame, endFrame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		root.getChildren().add(messageDay);
		mainStage.setScene(mainScene);
		mainStage.show();
	}
	
	/**
	 * This method checks whether or not the password matches confirm password in user input.
	 * @param password - The password the user has entered
	 * @param confirmPassword - The string user has entered in confirm password textbox
	 * @return True if they match, false otherwise
	 */
	public static boolean checkPassword(String password, String confirmPassword) {
		String test1 = password;
		String test2 = confirmPassword;
		if (test1.equals(test2)) {
			return true;
		} else if (!test1.equals(test2)) {
			return false;
		} else {
			return false;
		}
	}
	
	/**
	 * This method parses information to profile class.
	 * @param name The username the user has entered
	 * @param password Password that user has entered
	 * @param confirmPassword String user has entered to confirm password
	 */
	public static boolean createAccount(String name, String password, String confirmPassword) {
		if (name.equals("") || password.equals("") || confirmPassword.equals("")) {
			Alert empty = new Alert(AlertType.ERROR);
			empty.setHeaderText("A field is blank!");
			empty.setContentText("Please don't leave any fields blank! Try again.");
			empty.showAndWait();
			return false;
		} else if (checkPassword(password, confirmPassword) == true) {
			try {
				Profile.makeNewProfile(name, password);
				Alert working = new Alert(AlertType.INFORMATION);
				working.setTitle("Profile created!");
				working.setHeaderText("Are you ready to play?");
				working.setContentText("Your account has been created, "
						+ "you can now log in!");
				working.showAndWait();
				return true;
				
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Cannot find file");
				errorAlert.setContentText("Change directory and try again");
				errorAlert.showAndWait();
				return false;
			}
		} else {
			Alert passwordFailure = new Alert(AlertType.ERROR);
			passwordFailure.setHeaderText("Passwords don't match!");
			passwordFailure.setContentText("Passwords didn't match, "
					+ "user not created, try again.");
			passwordFailure.showAndWait();
			return false;
			
		}
		
		
	}
	
	/**
	 * This checks the user input matches a known account.
	 * @param username - The username that the user input
	 * @param password - The password that the user 
	 * @return Returns true if user exists, else returns false.
	 */
	public static boolean validateLogin(String username, String password) {
		String filename = System.getProperty("user.dir") + "\\Profiles\\" + 
					username + ".txt";
		File loginFile = new File(filename);
		try {
			Scanner reader = new Scanner(loginFile);
			reader.useDelimiter(",");
			String user = reader.next();
			String pass = reader.next();
			
			if (username.equals(user) && password.equals(pass)) {
				Alert loginworked = new Alert(AlertType.INFORMATION);
				loginworked.setHeaderText("Welcome!");
				loginworked.setContentText("Login correct, enjoy the game!");
				loginworked.showAndWait();
				reader.close();
				return true;
			} else {
				Alert passwordNotMatch = new Alert(AlertType.ERROR);
				passwordNotMatch.setHeaderText("Password does not match!");
				passwordNotMatch.setContentText("Password does not match username, "
						+ "try again!");
				passwordNotMatch.showAndWait();
				reader.close();
				return false;
			}
		} catch (FileNotFoundException e) {
			Alert noUser = new Alert(AlertType.ERROR);
			noUser.setHeaderText("User does not exist!");
			noUser.setContentText("Username cannot be found, try again! If"
					+ " you don't have an account, please create one to play!");
			noUser.showAndWait();
			return false;
		}
		
		
	}

}