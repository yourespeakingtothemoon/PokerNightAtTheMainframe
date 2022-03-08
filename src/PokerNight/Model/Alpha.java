package PokerNight.Model;

import PokerNight.Controller.Checks;
import PokerNight.View.Dialogue;
import PokerNight.View.UI;
import org.json.simple.parser.ParseException;

import java.io.IOException;

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
        float betLimit;
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
                break;
            default:
                raise = 11;
                fold = 4;
                check = 6;
                break;
        }
        int probScore = Checks.probScore(game.getRound(),super.pocket,game.getBoard());
                if(probScore>=10){betLimit=.15f;}
                if(probScore>=15){betLimit=.01f;}
                if(probScore>20){betLimit=0f;}
                else{betLimit=.2f;}
        return super.decide(fold,check,raise,game,ui,betLimit);
    }

    @Override
    public String toString() {
        return "Alpha"; //For testing
    }
}
