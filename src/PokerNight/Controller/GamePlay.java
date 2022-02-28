package PokerNight.Controller;

import PokerNight.Model.*;
import PokerNight.View.UI;

import java.util.ArrayList;

public class GamePlay {

    public void StartGame() {
        Game game = new Game();
        UI ui = new UI();

        game.getPlayers().add(new Human()); //for testing
        //Select players (one bot of each archetype) as well as the Human player
        //Add them to players

        MainGamePlayLoop(game, ui);
    }

    public void MainGamePlayLoop(Game game, UI ui) {
        for (int x = 0; x < game.getPlayers().size(); x++) { //Give each player an empty hand, $10k to bet
            game.getPlayers().get(x).setPocket(new ArrayList<>());
            game.getPlayers().get(x).setMoney(10000);
        }

        while (true) {
            game.NewRound(); //Resets just about everything

            for (int x = 0; x < game.getPlayers().size(); x++) { //Give each player 2 (pocket) cards
                game.getPlayers().get(x).setPocket(new ArrayList<>(DrawCard(2, game.getGameDeck())));
            }

            ui.DisplayGame(game);
            BettingRoundOne(game, ui);
            ui.DisplayGame(game);
            BettingRoundOne(game, ui); //For testing
        }

        //Drop players (probably from a new ArrayList like remainingPlayers) when they fold
        //Keep track of money --DONE--
        //Check each player's hand by combining board and pocket to determine best hand
        //After first betting round, do flop --DONE--
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

    public void BettingRoundOne(Game game, UI ui) { //Loops through players, doing turns, then does flop
        for (int x = 0; x < game.getPlayers().size(); x++) {
            game.setPot(game.getPot() + game.getPlayers().get(x).turn(game.getBoard(), game.getMinBet(), game.getGameDeck(), ui));
        }
        game.setBoard(DrawCard(3, game.getGameDeck()));
    }

    public ArrayList<Card> DrawCard(int cardsToDraw, ArrayList<Card> gameDeck) { //Draws x amount of cards, removing them from the deck
        ArrayList<Card> returnArrayList = new ArrayList<>();
        for (int x = 0; x < cardsToDraw; x++) {
            returnArrayList.add(gameDeck.get(0)); //Adds top card to returnList
            gameDeck.remove(0); //Removes top card from deck
        }
        return returnArrayList;
    }

    public ArrayList<AbsPlayer> SetRemainingPlayers(ArrayList<AbsPlayer> players) {
        ArrayList<AbsPlayer> remainingPlayers = new ArrayList<>();
        for (int x = 0; x < players.size(); x++) {
            if (!players.get(x).isSkipRound()) {
                remainingPlayers.add(players.get(x));
            }
        }
        return remainingPlayers;
    }
}
