package PokerNight.Model;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deckList = new ArrayList<>(52);

    public ArrayList<Card> GenerateDeck() { //Generates a full deck of 52 different cards
        for (int x = 0; x < Suit.values().length; x++) { //For each suit
            for (int y = 0; y < Rank.values().length; y++) { //For each rank
//                System.out.println(Rank.values()[y] + " of " + Suit.values()[x]);
                deckList.add(new Card(Suit.values()[x], Rank.values()[y])); //Creates a card of Suit, Rank; effectively each combination
            }
        }
        return deckList;
    }
}
