package PokerNight.Model.Players;

import PokerNight.Controller.Checks;
import PokerNight.Model.Game;
import PokerNight.View.Dialogue;
import PokerNight.View.UI;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Omega extends AbsPlayer {
    //constructors
    public Omega() {
    }

    public Omega(String name, int DID, int PID) throws IOException, ParseException {
        super.name = name;
        super.DialogueID = DID;
        super.personalityID = PID;
        Dialogue.printDialogue(super.DialogueID, super.rand.nextInt(4) + 1, super.personalityID, super.name);
    }

    @Override
    public int turn(Game game, UI ui) throws IOException, ParseException {
        int raise;
        int fold;
        int check;
        float betLimit;
        switch (game.getRound()) {
            case 1:
                raise = 13;
                fold = 0;
                check = 3;
                break;
            case 2:
            case 3:
            case 4:
                raise = 19;
                fold = 6;
                check = 10;
                break;
            default:
                raise = 13;
                fold = 4;
                check = 6;
                break;
        }
        int probScore = Checks.probScore(game.getRound(), super.pocket, game.getBoard());
        betLimit = .6f;
        if (probScore >= 10 && probScore < 15) {
            betLimit = .50f;
        }
        if (probScore >= 15 && probScore < 20) {
            betLimit = .2f;
        }
        if (probScore >= 20) {
            betLimit = .1f;
        }
        return super.decide(fold, check, raise, game, ui, betLimit);
    }

    @Override
    public String toString() {
        return "Omega"; //For testing
    }
}
