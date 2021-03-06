package PokerNight.Model.Players;

import PokerNight.Controller.Checks;
import PokerNight.Model.Game;
import PokerNight.View.Dialogue;
import PokerNight.View.UI;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Beta extends AbsPlayer{
    //constructor
    public Beta(String name,int DID, int PID) throws IOException, ParseException {
        super.name = name;
        super.DialogueID = DID;
        super.personalityID = PID;
        Dialogue.printDialogue(super.DialogueID, super.rand.nextInt(4) + 1, super.personalityID, super.name);
    }

    public Beta() {
    }

    @Override

    public int turn(Game game, UI ui) throws IOException, ParseException {
        int raise;
        int fold;
        int check;
        float betLimit;
        switch (game.getRound()) {
            case 1:
                raise = 16;
                fold = 0;
                check = 3;
                break;
            case 2:
            case 3:
            case 4:
                raise = 21;
                fold = 10;
                check = 16;
                break;
            default:
                raise = 16;
                fold = 4;
                check = 6;
                break;
        }
        int probScore = Checks.probScore(game.getRound(), super.pocket, game.getBoard());
        betLimit = .8f;
        if (probScore >= 10 && probScore < 15) {
            betLimit = .75f;
        }
        if (probScore >= 15 && probScore < 20) {
            betLimit = .5f;
        }
        if (probScore >= 20) {
            betLimit = .25f;
        }
        return super.decide(fold, check, raise, game, ui, betLimit);
    }

    @Override
    public String toString() {
        return "Beta"; //For testing
    }
}
