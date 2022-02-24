package PokerNight.Model;

public enum Suit {
    HEARTS("♥"),
    DIAMONDS("♦"),
    SPADES("♠"),
    CLUBS("♣");
    private String value;

    Suit(String s) {
        this.value = s;
    }

    @Override
    public String toString() { //Prints the character instead of HEARTS, DIAMONDS, etc.
        return this.value;
    }
}
