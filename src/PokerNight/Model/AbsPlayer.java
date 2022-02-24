package PokerNight.Model;

import java.util.ArrayList;

abstract class AbsPlayer {
    //Player Properties
    protected String name;
    protected int DialogueID; //for calling character specfic/player type specfifc dialogue
    protected ArrayList<Card> hand;
    protected String catchphrase;

    public void drawCards(ArrayList<Card> cardsGiven){
        hand = cardsGiven;
    }

    abstract public void turn(ArrayList<Card> openCards);
}
