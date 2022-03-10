package PokerNight.Model.Cards;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deckList = new ArrayList<>(52);

    public ArrayList<Card> GenerateDeck(ArrayList<Card> deckList) { //Generates a full deck of 52 different cards
        for (int x = 0; x < Suit.values().length; x++) { //For each suit
            for (int y = 0; y < Rank.values().length; y++) { //For each rank
//                System.out.println(Rank.values()[y] + " of " + Suit.values()[x]);
                deckList.add(new Card(Suit.values()[x], Rank.values()[y])); //Creates a card of Suit, Rank; effectively each combination
            }
        }
        return deckList;
    }

    public ArrayList<Card> getDeckList() {
        return deckList;
    }

    public void setDeckList(ArrayList<Card> deckList) {
        this.deckList = deckList;
    }
}
