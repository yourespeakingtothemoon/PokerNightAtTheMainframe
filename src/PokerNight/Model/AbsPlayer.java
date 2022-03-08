package PokerNight.Model;

import PokerNight.Controller.Checks;
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

    protected int decide(int fold,int check, int raise, Game game, UI ui, float percentKeep){
        int raiseAmt = this.getMoney() - game.getMinBet();
        //Call
        int probabilityScore = Checks.probScore(game.getRound(), this.pocket, game.getBoard());
        if (probabilityScore > check && probabilityScore < raise) {
            if (this.getMoney() >= game.getMinBet()) {
                this.setMoney(this.getMoney() - game.getMinBet());
                return game.getMinBet();
            }
            int returnAmt = this.getMoney(); //If the player doesn't have enough to call, they just put in their max amount.
            this.setMoney(0);
            return returnAmt;
        }
        //raise
        if (probabilityScore >= raise) {
            if (this.getMoney() >= game.getMinBet()) {
                int betAmt = rand.nextInt(((raiseAmt)-Math.round(raiseAmt*percentKeep))  + 1) + game.getMinBet();
                game.setMinBet(betAmt);
                this.setMoney(this.getMoney() - game.getMinBet());
                return betAmt;
            }
        }
        //fold
        if (probabilityScore <= fold) {
            this.setSkipRound(true);
            return 0;
        }
        //Check
        else{
            return 0; //exit round without folding
        }

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
}
