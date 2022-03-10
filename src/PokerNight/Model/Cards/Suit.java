package PokerNight.Model.Cards;

public enum Suit {
    HEARTS("\ue299a0"),
    DIAMONDS("\u2666"),
    SPADES("\u2660"),
    CLUBS("\u2663");
    private String value;

    Suit(String s) {
        this.value = s;
    }

    @Override
    public String toString() { //Prints the character instead of HEARTS, DIAMONDS, etc.
        return this.value;
    }
}
