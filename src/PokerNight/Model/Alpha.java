package PokerNight.Model;

import PokerNight.View.Dialogue;
import PokerNight.View.UI;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Random;

public class Alpha extends AbsPlayer{
    //constructor
    public Alpha(String name,int DID, int PID) throws IOException, ParseException {
        super.name = name;
        super.DialogueID=DID;
        super.personalityID=PID;
        Dialogue.printDialogue(super.DialogueID,super.rand.nextInt(4)+1,super.personalityID,super.name);
    }
    public Alpha(){}
    @Override
    public int turn(Game game, UI ui) {
        int raise;
        int fold;
        int check;
        switch (game.getRound()) {
            case 1:
                raise = 10;
                fold = 0;
                check = 3;
                break;
            case 2:
            case 3:
            case 4:

                raise = 13;
                fold = 5;
                check = 7;
            default:
                raise = 11;
                fold = 4;
                check = 6;
        }
        return super.decide(fold,check,raise,game,ui);
    }

    @Override
    public String toString() {
        return "Alpha"; //For testing
    }
}
