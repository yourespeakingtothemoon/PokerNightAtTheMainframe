package PokerNight.DAL;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DAPoker {

    /*moon's to do:
    - make JSON with all dialogue and text required for characters with
    key value format being like bluff-ID-RAND
    - create a method that pulls from passed in info using the ID number and a Randomly generated number
    *******IF TIME PERMITS*******
    - create a persistent high score system using another JSON file.
     */

    public static String pullDialogue(int ID,int val) throws IOException, ParseException {
        FileReader file = new FileReader("dialogue.json");
        JSONObject line = (JSONObject) new JSONParser().parse(file);
        JSONObject set = (JSONObject) line.get("set-"+ID);
        return (String) set.get(String.valueOf(val));

    }



}
