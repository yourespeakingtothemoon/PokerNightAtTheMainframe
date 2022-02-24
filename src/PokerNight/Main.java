package PokerNight;

import PokerNight.Model.Deck;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck.GenerateDeck(new ArrayList<>()));
    }
}
