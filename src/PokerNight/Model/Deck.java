package PokerNight.Model;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deckList = new ArrayList<>(52);

    public ArrayList<Card> GenerateDeck() {
        for (int x = 0; x < Suit.values().length; x++) {
            for (int y = 0; x < Rank.values().length; y++) {
                System.out.println(Rank.values()[y] + " of " + Suit.values()[x]);
                deckList.add(new Card(Suit.values()[x], Rank.values()[y]));
            }
        }
        return deckList;
    }
}
