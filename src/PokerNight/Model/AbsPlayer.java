package PokerNight.Model;

import java.util.ArrayList;

public abstract class AbsPlayer {
    //Player Properties
    protected String name;
    protected int DialogueID; //for calling character specfic/player type specfifc dialogue
    protected ArrayList<Card> hand;

    public void drawCards(ArrayList<Card> cardsGiven){
        hand = cardsGiven;
    }

    abstract public void turn(ArrayList<Card> openCards);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDialogueID() {
        return DialogueID;
    }

    public void setDialogueID(int dialogueID) {
        DialogueID = dialogueID;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}
