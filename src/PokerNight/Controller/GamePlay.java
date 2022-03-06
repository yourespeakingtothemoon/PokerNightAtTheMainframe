package PokerNight.Controller;

import PokerNight.Model.*;
import PokerNight.View.UI;

import java.util.ArrayList;

public class GamePlay {

    public void StartGame() {
        Game game = new Game();
        UI ui = new UI();

        game.getPlayers().add(new Human()); //for testing
        game.getPlayers().add(new Omega()); //for testing
        game.getPlayers().add(new Sigma()); //for testing
        game.getPlayers().add(new Alpha()); //for testing
        game.getPlayers().add(new Beta()); //for testing
        //Select players (one bot of each archetype) as well as the Human player
        //Add them to players

        MainGamePlayLoop(game, ui);
    }

    public void MainGamePlayLoop(Game game, UI ui) {
        for (int x = 0; x < game.getPlayers().size(); x++) { //Give each player an empty hand, $10k to bet
            game.getPlayers().get(x).setPocket(new ArrayList<>()); //this could be added to a constructor instead for new AbsPlayers
            game.getPlayers().get(x).setMoney(10000);
        }

        while (true) {
                //If they're human, save high score, etc.
            RemovePlayers(game); //Permanent removal of players

            for (int i = 0; i < game.getPlayers().size(); i++) {
                if (game.getPlayers().get(i).getMoney() == 50000 || playersInGame(game) == 1) { //If one player has all money
                    ui.DisplayWinner(game.getPlayers().get(i));
                    return;
                }
            }

            game.NewRound(); //Resets just about everything

            for (int x = 0; x < game.getRemainingPlayers().size(); x++) { //Give each player 2 (pocket) cards
                game.getPlayers().get(x).setPocket(new ArrayList<>(DrawCard(2, game.getGameDeck())));
                game.getPlayers().get(x).setSkipRound(false);
            }

            ui.DisplayGame(game);
            for (int x = 0; x < 4; x++) {
                BettingRound(game, ui);
                SetRemainingPlayers(game);
                ui.DisplayGame(game);
            }
            //Do checks, pay out the winner, end round
            //Still need to implement checks
            for (int y = 0; y < game.getRemainingPlayers().size(); y++) {
                if (!game.getRemainingPlayers().get(y).isOutOfGame()) {
                    game.setWinner(game.getRemainingPlayers().get(y));
                }
            }
            game.getWinner().setMoney(game.getWinner().getMoney() + game.getPot()); //Gives the winner the pot
            ui.DisplayEndRound(game);
        }

        //Choose person to start, make 2 starting players pay big and small blind
            //Sort players in game.getPlayers() so they start at a specific person? --CANNOT - human player has to stay index 0--
        //Drop players (probably from a new ArrayList like remainingPlayers) when they fold --DONE--
        //Keep track of money --DONE--
        //Check each player's hand by combining board and pocket to determine best hand ****
        //After first betting round, do flop --DONE--
        //Do another betting round, do turn --DONE--
        //Do another betting round, do river --DONE--
        //Do another betting round, show cards and determine winner of pot --MOSTLY DONE--
        //Loop, resetting remainingPlayers to every player that still has money and increasing blinds --DONE--
        //Check at the beginning of a round if only one player has money, throw win condition --DONE--
            //If Human is the last remaining player, allow them to add acronym to save high score
            //We need to figure out how or if we want to do score lmao
            //If human player is out, allow them to spectate or leave the game --MAYBE--

        //Return to main menu

    }

    public void BettingRound(Game game, UI ui) { //Loops through players, doing turns, then does flop
        game.setRound(game.getRound() + 1); //Adds 1 to the round
        if (game.getRemainingPlayers().size() <= 1) {
            return;
        }
        for (int x = 0; x < game.getRemainingPlayers().size(); x++) {
            if (!game.getRemainingPlayers().get(x).isOutOfGame()) {
                game.setPot(game.getPot() + game.getRemainingPlayers().get(x).turn(game, ui));
            }
        }
        if (game.getRound() == 1) { //If it's round 1, do the Flop
            game.getMuck().add(DrawCard(1, game.getGameDeck()).get(0)); //Throws one card out, as per poker rules
            game.setBoard(DrawCard(3, game.getGameDeck())); //The Flop
        } else if (game.getRound() < 4) { //If it's any other round besides the final betting round, only add 1 card
            game.getMuck().add(DrawCard(1, game.getGameDeck()).get(0));
            game.getBoard().add(DrawCard(1, game.getGameDeck()).get(0)); //Draws 1 card, adds it to the board
        }
    }

    public ArrayList<Card> DrawCard(int cardsToDraw, ArrayList<Card> gameDeck) { //Draws x amount of cards, removing them from the deck
        ArrayList<Card> returnArrayList = new ArrayList<>();
        for (int x = 0; x < cardsToDraw; x++) {
            returnArrayList.add(gameDeck.get(0)); //Adds top card to returnList
            gameDeck.remove(0); //Removes top card from deck
        }
        return returnArrayList;
    }

    //May move these to the Game class
    public void SetRemainingPlayers(Game game) { //Sets the remaining players for the TURN, not the game
        for (int x = 0; x < game.getRemainingPlayers().size(); x++) {
            if (game.getRemainingPlayers().get(x).isSkipRound()) {
                game.getRemainingPlayers().remove(x);
            }
        }
    }

    public void RemovePlayers(Game game) { //Permanently removes players from the game if their money is 0
        for (int x = 0; x < game.getPlayers().size(); x++) {
            if (game.getPlayers().get(x).getMoney() <= 0 ) { //Should never be less than 0, but just in case
                game.getPlayers().get(x).setOutOfGame(true);
            }
        }
    }

    public int playersInGame(Game game) {
        int playersInGame = 5;
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (game.getPlayers().get(i).isOutOfGame()) {
                playersInGame--;
            }
        }
        return playersInGame;
    }
}
