package PokerNight.DAL;


import PokerNight.Model.Players.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
//character gen take in archetype and a random number 1- however many players in that archetype are available.
    public static AbsPlayer playerPull(String archetype, int playerNumber) throws IOException, ParseException {
            FileReader file = new FileReader("players.json");
            JSONObject set = (JSONObject) new JSONParser().parse(file);
            JSONObject playerInfo = (JSONObject) set.get(archetype+playerNumber);
            Long longDialogue = (Long) playerInfo.get("ID");
            Long longPersonality=(Long) playerInfo.get("PID");
        switch(archetype){
            case "alpha":
                return new Alpha((String) playerInfo.get("name"), longDialogue.intValue(),longPersonality.intValue());
            case "beta":
                return new Beta((String) playerInfo.get("name"), longDialogue.intValue(),longPersonality.intValue());
            case "omega":
                return new Omega((String) playerInfo.get("name"), longDialogue.intValue(),longPersonality.intValue());
            default:
                return new Sigma((String) playerInfo.get("name"), longDialogue.intValue(),longPersonality.intValue());
        }
    }


}
