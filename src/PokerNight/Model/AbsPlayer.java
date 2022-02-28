package PokerNight.Model;

import java.util.ArrayList;

public abstract class AbsPlayer {
    //Player Properties
    protected String name;
    protected int money;

    protected int DialogueID; //for calling character specific/player type specific dialogue
    protected ArrayList<Card> pocket;
    protected String catchphrase;

    public void drawCards(ArrayList<Card> cardsGiven){
        pocket = cardsGiven;
    }

    abstract public void turn(ArrayList<Card> board, int blinds, ArrayList<Card> gameDeck); //Implemented in each concrete class

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
