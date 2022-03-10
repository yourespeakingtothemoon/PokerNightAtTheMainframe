package PokerNight.Model.Players;

import PokerNight.Model.Game;
import PokerNight.View.Dialogue;
import PokerNight.View.UI;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Sigma extends AbsPlayer {
    //Constructor
    public Sigma() {

    }

    public Sigma(String name, int ID, int PID) throws IOException, ParseException {
       super.DialogueID = ID;
       super.name=name;
       super.personalityID=PID;
       sigmaStringset(super.rand.nextInt(9)+1);
    }

    //turn abstract thingy -- This is pretty much how it should work for all archetypes
    //Main difference is the way they determine their action
    @Override
    public int turn(Game game, UI ui) throws IOException, ParseException { //sigma grindset
        //#heboughtthebank
        if(rand.nextInt(2)==1){
        sigmaStringset(super.rand.nextInt(10 + 1));}
        while (true) {
            switch (super.rand.nextInt(4)) {
                case 0: //Call
                    if (this.getMoney() >= game.getMinBet()) {
                        this.setMoney(this.getMoney() - game.getMinBet());
                        ui.printAction(this.getName(), "calls");
                        return game.getMinBet();
                    }
                    int returnAmt = this.getMoney();
                    this.setMoney(0);

                    ui.printAction(this.getName(), "calls and says, \"all in.\"");
                    return returnAmt;
                case 1: //raise
                    if (this.getMoney() >= game.getMinBet()) { //Disallows the Sigma to bet more than they have
                        int betAmt = super.rand.nextInt((this.getMoney() - game.getMinBet()) + 1) + game.getMinBet();
                        game.setMinBet(betAmt);
                        this.setMoney(this.getMoney() - game.getMinBet());
                        ui.printAction(this.getName(), "raises to " + betAmt);
                        return betAmt;
                    }
                case 2: //fold
                    this.setSkipRound(true);
                    ui.printAction(this.getName(), "folds");
                    return 0;
                case 3: //Check
                    if (!(game.getRound() == 1)) {
                        ui.printAction(this.getName(), "checks");
                        return 0; //Stay in the game without betting
                    }
            }
        }
    }

    private void sigmaStringset(int otherSpeak) throws IOException, ParseException { //Still needs to be implemented
        int ID;
        if (otherSpeak == super.rand.nextInt(9) + 1) {
            ID = super.rand.nextInt(12) + 1;

        } else {
            ID = super.DialogueID;
        }
        //return string based on new ID and random number
        int stringVal = super.rand.nextInt(10) + 1;

        Dialogue.printDialogue(ID,stringVal,super.personalityID,super.name);
        // after completing the dialog print class and methods, send in i.e. printSigmaDia(ID,stringVal)
    }

    @Override
    public String toString() {
        return "Sigma"; //For testing
    }
}
