import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * This class plays all of the game sounds that are needed inside of the game.
 * @author George Cook
 * @version 1.4
 */
public class Sound {

    /**
     * This method get the sound from the correct directory and plays it.
     * @param soundFile The exact name of the file that needs to be played.
     * @throws IOException This exception is thrown if the required file is not found.
     */
    private static void playSound(String soundFile) throws IOException{

        File file = new File(soundFile);
        URL url = null;
        if (file.canRead()) {url = file.toURI().toURL();} //Creating the file path to the sound file.
        System.out.println(url);
        AudioClip clip = Applet.newAudioClip(url);
        clip.play();
        System.out.println("should've played by now");
    }

    /**
     * This method uses a switch statement to find the required file that needs to be played.
     * @param sound A string that gets the corresponding sound file name from the switch statement.
     */
    public static void getSound(String sound){

        switch(sound) {

            case "Portal":
                try {
                    playSound("LoadSound.wav");
                    playSound("Teleporter1.wav");
                } catch (Exception ex){
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();

                }
                break;

            case "TokenPickUp":
                try {
                    playSound("LoadSound.wav");
                    playSound("PickUp1.wav");
                } catch (Exception ex){
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();

                }
                break;

            case "MainMusic":
                try {
                    playSound("LoadSound.wav");
                    playSound("MenuMusic.wav");
                } catch (Exception ex){
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();

                }
                break;

            case "Death":
                try{
                    playSound("LoadSound.wav");
                    playSound("Death.wav");
                }catch (Exception ex){
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }

            case "OpenDoor":
                try{
                    playSound("LoadSound.wav");
                    playSound("OpenDoor.wav");
                }catch (Exception ex){
                    System.out.println("Error with Playing sound.");
                    ex.printStackTrace();
                }


        }
    }
}


