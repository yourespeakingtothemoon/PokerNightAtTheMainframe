package PokerNight.Model;

import PokerNight.View.UI;

import java.util.ArrayList;

public abstract class AbsPlayer {
    //Player Properties
    protected String name;
    protected int money;
    protected boolean skipRound = false;

    protected int DialogueID; //for calling character specific/player type specific dialogue
    protected ArrayList<Card> pocket;
    protected String catchphrase;

    public void drawCards(ArrayList<Card> cardsGiven){
        pocket = cardsGiven;
    }

    abstract public int turn(Game game, UI ui); //Implemented in each concrete class
    //Board, gameDeck not needed for Human. Remove if not needed for other players

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
