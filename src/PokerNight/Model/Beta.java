package PokerNight.Model;

import PokerNight.View.Dialogue;
import PokerNight.View.UI;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Random;

public class Beta extends AbsPlayer{
    //contructor
    public Beta(String name,int DID, int PID) throws IOException, ParseException {
        super.name = name;
        super.DialogueID=DID;
        super.personalityID=PID;
        Dialogue.printDialogue(super.DialogueID,super.rand.nextInt(4)+1,super.personalityID,super.name);
    }
    public Beta(){}
    @Override
    public int turn(Game game, UI ui) {
        int raise;
        int fold;
        int check;
        switch (game.getRound()) {
            case 1:
                raise = 16;
                fold = 0;
                check = 3;
                break;
            case 2:
            case 3:
            case 4:
                raise = 19;
                fold = 10;
                check = 13;
            default:
                raise = 16;
                fold = 4;
                check = 6;
        }
        return super.decide(fold,check,raise,game,ui);
    }

    @Override
    public String toString() {
        return "Beta"; //For testing
    }
}
