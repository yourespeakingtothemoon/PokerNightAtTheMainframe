package PokerNight.Model;

public class Card {
    private Suit suit; //HEARTS, DIAMONDS, SPADES, CLUBS
    private Rank rank; //ACE, DEUCE, THREE... JACK, QUEEN, KING

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return this.rank + " of " + this.suit; //e.g. ACE of CLUBS, KING of HEARTS
    }
}
