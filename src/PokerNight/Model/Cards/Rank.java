package PokerNight.Model.Cards;

public enum Rank {
    ACE("A",1),
    DEUCE("2",2),
    THREE("3",3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("J",11),
    QUEEN("Q",12),
    KING("K",13);
    private String value;
    private int numVal;

    Rank(String s, int i) {
        this.value = s;
        this.numVal=i;
    }
    public int getNumVal(){
        return this.numVal;
    }

    @Override
    public String toString() { //Prints the character instead of ACE, DEUCE, etc.
        return this.value;
    }
}
