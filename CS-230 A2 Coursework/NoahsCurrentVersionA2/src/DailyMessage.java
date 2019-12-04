import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class gets the coded string from the intended website,
 * Decodes it and then gets the message of the day from this.
 * @author George Cook
 * @version 1.2
 */

public class DailyMessage {

    /**
     * - This method gets the coded string from the website
     * and parses it to the decode method.
     * - No side effects.
     * @return The result of method call decode.
     */
    public static String getPuzzle(){
        String puzzle = "";

        try {

            URL url = new URL("http://cswebcat.swan.ac.uk/puzzle");

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
                puzzle = line;
                //System.out.println(line); Debug Message.
            }
            in.close();

        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }



        return decode(puzzle) ;


    }

    /**
     * - This funtion takes in a string and decodes it
     * based on a set of rules.
     * - No side effects
     * @param puzzle A String of chars that need to be decoded.
     * @return The result of the method call getMessage.
     */
    private static String decode(String puzzle){
        int asciiValue;
        String decodedString = "";
        char[] puzzleChar = new char[puzzle.length()];

        for (int i = 0; i < puzzle.length(); i++){
            puzzleChar[i] = puzzle.charAt(i);
        }

        for (int i = 1; i <= puzzleChar.length; i++) {
            if (i % 2 == 0) {
                asciiValue = ((int)puzzleChar[i-1] - 1 - 65) % 26 + 65;
                if(asciiValue == 64){
                    asciiValue = 90;
                }
                puzzleChar[i-1] = (char) asciiValue;
            } else {
                asciiValue = ((int)puzzleChar[i-1] + 1 - 65) % 26 + 65;
                puzzleChar[i-1] = (char) asciiValue;
            }
        }

        for(int i = 0; i < puzzleChar.length; i++){
            decodedString = decodedString + puzzleChar[i];
        }

        return getMessage(decodedString);
    }


    /**
     * - Gets the daily message that corresponds
     * with the decoded message.
     * - No side effects.
     * @param decodedString The decoded string that gets put in to a URL.
     * @return That daily message that is on the website, as a String.
     */
    private static String getMessage(String decodedString){
        String dailyMessage = "";
        String dailyMessageFormated = "";

        try {

            URL url = new URL("http://cswebcat.swan.ac.uk/message?solution=" + decodedString);

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
                dailyMessage = line;
                //System.out.println(line); Debug Message
            }
            in.close();

        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }

        for ( int i = 0; i <= dailyMessage.length()-27; i++){
            dailyMessageFormated = dailyMessageFormated + dailyMessage.charAt(i);
        }

        return dailyMessageFormated;

    }



}
