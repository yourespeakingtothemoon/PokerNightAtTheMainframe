package PokerNight.Controller;

import PokerNight.Model.AbsPlayer;
import PokerNight.Model.Card;
import PokerNight.Model.Deck;
import PokerNight.View.UI;

import java.util.ArrayList;
import java.util.Collections;

public class GamePlay {
    Deck deck = new Deck();

    public void StartGame() {
        UI ui = new UI();
        deck.setDeckList(deck.GenerateDeck(new ArrayList<>())); //Generates deck, shuffles it
        Collections.shuffle(deck.getDeckList());

        ArrayList<AbsPlayer> players = new ArrayList<>();
        //Select players (one bot of each archetype) as well as the Human player
        //Add them to players

        MainGamePlayLoop(deck.getDeckList(), players, ui);
    }

    public void MainGamePlayLoop(ArrayList<Card> gameDeck, ArrayList<AbsPlayer> players, UI ui) {
        ArrayList<Card> board = new ArrayList<>(); //Initialize board ArrayList, blinds/bet amount, pot
        ArrayList<Card> burnedCards = new ArrayList<>();
        int pot = 0;
        int blinds = 0;

        for (int x = 0; x < players.size(); x++) { //Give each player 2 (pocket) cards, $10k to bet
            players.get(x).setMoney(10000);
            players.get(x).setPocket(new ArrayList<>(DrawCard(2, gameDeck)));
        }

        while (true) {
            ///////////////////////////// Reset for new round /////////////////////////////
            blinds += 200; //Increase blinds -- Normally is increased every 15-20 minutes, we'll just increase every round
            board.clear(); //Reset board, burned cards, pocket cards, deck, pot
            burnedCards.clear();
            for (int x = 0; x < players.size(); x++) {
                players.get(x).getPocket().clear();
            }
            gameDeck.clear();
            deck.GenerateDeck(gameDeck);
            pot = 0;
            ///////////////////////////// Start game /////////////////////////////
            ui.DisplayGame();
            BettingRoundOne(players, gameDeck, board, pot, blinds);
        }

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

    public void BettingRoundOne(ArrayList<AbsPlayer> players, ArrayList<Card> gameDeck, ArrayList<Card> board, int pot, int blinds) {
        for (int x = 0; x < players.size(); x++) { //loop through players, doing turns
            //May also add in dialogue here
            players.get(x).turn(board, blinds, gameDeck);
        }
        board.addAll(DrawCard(3, gameDeck)); //The Flop
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
