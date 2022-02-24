package PokerNight.Controller;

import PokerNight.Model.Card;
import PokerNight.Model.Deck;

import java.util.ArrayList;
import java.util.Collections;

public class GamePlay {
    Deck deck = new Deck();

    public void StartGame() {
        ArrayList<Card> gameDeck = deck.GenerateDeck(new ArrayList<>());
        Collections.shuffle(gameDeck);
    }

    public Card DrawCard(ArrayList<Card> gameDeck) { //Draws the top card, removing it from the deck and returning it
        Card returnCard = gameDeck.get(0);
        gameDeck.remove(0);
        return returnCard;
    }
}
