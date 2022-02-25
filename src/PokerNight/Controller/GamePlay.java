package PokerNight.Controller;

import PokerNight.Model.AbsPlayer;
import PokerNight.Model.Card;
import PokerNight.Model.Deck;

import java.util.ArrayList;
import java.util.Collections;

public class GamePlay {
    Deck deck = new Deck();

    public void StartGame() {
        deck.setDeckList(deck.GenerateDeck(new ArrayList<>())); //Generates deck, shuffles it
        Collections.shuffle(deck.getDeckList());

        ArrayList<AbsPlayer> players = new ArrayList<>();
        //Select players (one bot of each archetype) as well as the Human player
        //Add them to players

        MainGamePlayLoop(deck.getDeckList(), players);
    }

    public void MainGamePlayLoop(ArrayList<Card> gameDeck, ArrayList<AbsPlayer> players) {
        //Initialize board ArrayList, blinds/bet amount, pot
        //Give each player 2 (pocket) cards, $10k to bet

        //Increase blinds
        //Reset board, burned cards, pocket cards, deck, pot
        //loop through players, doing turns
            //If player folds, allow them to skip to next round
        //Drop players (probably from a new ArrayList like remainingPlayers) when they fold
        //Keep track of money, check each player's hand by combining board and pocket to determine best hand
        //After first betting round, do flop
        //Do another betting round, do turn
        //Do another betting round, do river
        //Do another betting round, show cards and determine winner of pot
        //Loop, resetting remainingPlayers to every player that still has money and increasing blinds
        //Check at the beginning of a round if only one player has money, throw win condition
            //If Human is the last remaining player, allow them to add acronym to save high score
            //We need to figure out how we want to do score lmao
            //If human player is out, allow them to spectate or leave the game

        //Return to main menu

    }

    public ArrayList<Card> DrawCard(int cardsToDraw, ArrayList<Card> gameDeck) { //Draws x amount of cards, removing them from the deck
        ArrayList<Card> returnArrayList = new ArrayList<>();
        for (int x = 0; x < cardsToDraw; x++) {
            returnArrayList.add(gameDeck.get(0)); //Adds top card to returnList
            gameDeck.remove(0); //Removes top card from deck
        }
        return returnArrayList;
    }
}
