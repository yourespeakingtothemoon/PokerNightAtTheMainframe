package PokerNight.Model;

public enum Rank {
    ACE("A"),
    DEUCE("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");
    private String value;

    Rank(String s) {
        this.value = s;
    }

    @Override
    public String toString() { //Prints the character instead of ACE, DEUCE, etc.
        return this.value;
    }
}
