package PokerNight.Model;

import PokerNight.Controller.Checks;
import PokerNight.View.Dialogue;
import PokerNight.View.UI;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public abstract class AbsPlayer {
    //Player Properties
    protected String name;
    protected int money;
    protected boolean skipRound = false;
    protected boolean outOfGame = false;

    protected int DialogueID; //for calling character specific/player type specific dialogue
    protected int personalityID;
    protected ArrayList<Card> pocket;
    protected Random rand = new Random(); //randomizer to decide what to say

    abstract public int turn(Game game, UI ui) throws IOException, ParseException; //Implemented in each concrete class

    protected int decide(int fold, int check, int raise, Game game, UI ui, float percentKeep) throws IOException, ParseException {
        int raiseAmt = this.getMoney() - game.getMinBet();
        int probabilityScore = Checks.probScore(game.getRound(), this.pocket, game.getBoard());
        if(flipACoin()>=50){
            Dialogue.printDialogue(this.DialogueID,rand.nextInt(8-5)+5,this.personalityID,this.name);
        }
        //Call
        if (probabilityScore > check && probabilityScore < raise) {
            if (this.getMoney() >= game.getMinBet()) {
                this.setMoney(this.getMoney() - game.getMinBet());
                ui.printAction(this.name, "calls");
                return game.getMinBet();
            }
        }
        //raise
        if (probabilityScore >= raise) {

            if (this.getMoney() >= game.getMinBet()) {
                int betAmt = rand.nextInt(((raiseAmt) - Math.round(raiseAmt * percentKeep)) + 1) + game.getMinBet();
                game.setMinBet(betAmt);
                this.setMoney(this.getMoney() - game.getMinBet());
                ui.printAction(this.name, "raises to " + betAmt);
                return betAmt;
            }
        }
        //fold
        if (probabilityScore <= fold) {
            ui.printAction(this.name, "folds");
            this.setSkipRound(true);
            return 0;
        }
        //Check
        if (game.getRound() > 1) {
            ui.printAction(this.name, "checks");
            return 0; //exit round without folding
        }
        //If a bot tries to bet or call but doesn't have the money to do it, they will just go all in
        ui.printAction(this.name, "calls and says, \"all in.\"");
        int returnAmt = this.getMoney(); //If the player doesn't have enough to call, they just put in their max amount.
        this.setMoney(0);
        return returnAmt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isSkipRound() {
        return skipRound;
    }

    public void setSkipRound(boolean skipRound) {
        this.skipRound = skipRound;
    }

    public boolean isOutOfGame() {
        return outOfGame;
    }

    public void setOutOfGame(boolean outOfGame) {
        this.outOfGame = outOfGame;
    }

    public int getDialogueID() {
        return DialogueID;
    }

    public void setDialogueID(int dialogueID) {
        DialogueID = dialogueID;
    }

    public ArrayList<Card> getPocket() {
        return pocket;
    }

    public void setPocket(ArrayList<Card> pocket) {
        this.pocket = pocket;
    }

    //flip a coin any coin for deciding if character is gonna speak or not
    protected int flipACoin() {
        Random rand=new Random();
        return rand.nextInt(100)+1;
    }
}

