import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * This class plays all of the game sounds that are needed inside of the game.
 * @author George Cook
 * @version 1.8
 */
public class Sound {

    /**
     * This method get the sound from the correct directory and plays it.
     * @param soundFile The exact name of the file that needs to be played.
     * @throws IOException This exception is thrown if the required file is not found.
     */
    private static void playSound(String soundFile) throws IOException {
        File file = new File(soundFile);
        URL url = null;
        if (file.canRead()) {
            url = file.toURI().toURL();
        } //Creating the file path to the sound file.
        //System.out.println(url);
        AudioClip clip = Applet.newAudioClip(url);
        clip.play();
        //System.out.println("should've played by now");
    }

    /**
     * This method uses a switch statement to find the required file that needs to be played.
     * @param sound A string that gets the corresponding sound file name from the switch statement.
     */
    public static void getSound(String sound) {
        switch(sound) {

            case "Death":
                try {
                    playSound("Sounds\\Death1.wav");
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                break;

            case "HitWall":
                try {
                    playSound("Sounds\\WalkInToWall.wav");
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                break;

            case "KeyPickUp":
                try {
                    playSound("Sounds\\KeyPickUp1.wav");
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                break;

            case "LevelCompleted":
                try {
                    playSound("Sounds\\Goal1.wav");
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                break;

            case "DoorOpen":
                try {
                    playSound("Sounds\\DoorOpen1.wav");
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                break;

            case "Portal":
                try {
                    playSound("Sounds\\Teleporter1.wav");
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                break;

            case "TokenPickUp":
                try {
                    playSound("Sounds\\PickUp1.wav");
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                break;

            case "MainMusic":
                try {
                    playSound("Sounds\\MenuMusic.wav");
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + sound);
        }
    }
}