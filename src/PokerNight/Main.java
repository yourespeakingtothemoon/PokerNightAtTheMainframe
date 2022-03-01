package PokerNight;

import PokerNight.Controller.StartApp;
import PokerNight.DAL.DAPoker;
import PokerNight.View.Dialogue;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

       /* Dialogue.printDialogue(1,9,1,"AM");
        Dialogue.printDialogue(1,3,1,"AM");*/

        StartApp startApp = new StartApp();
        startApp.MainMenu();
    }
}
