package PokerNight.Controller;

import PokerNight.Model.*;
import PokerNight.View.UI;

import java.util.ArrayList;

public class GamePlay {

    public void StartGame() throws InterruptedException {
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

    public void MainGamePlayLoop(Game game, UI ui) throws InterruptedException {
        for (int x = 0; x < game.getPlayers().size(); x++) { //Give each player an empty hand, $10k to bet
            game.getPlayers().get(x).setPocket(new ArrayList<>()); //this could be added to a constructor instead for new AbsPlayers
            game.getPlayers().get(x).setMoney(10000);
        }

        while (true) {
            //Check if there's only 1 player in game.getPlayers() -- if so, they win!
                //If they're human, save high score, etc.
            RemovePlayers(game); //Permanent removal of players
            game.NewRound(); //Resets just about everything
            System.out.println(game.getRemainingPlayers());

            for (int x = 0; x < game.getPlayers().size(); x++) { //Give each player 2 (pocket) cards
                game.getPlayers().get(x).setPocket(new ArrayList<>(DrawCard(2, game.getGameDeck())));
            }

            for (int x = 0; x < 4; x++) {
                ui.DisplayGame(game);
                BettingRound(game, ui);
                SetRemainingPlayers(game);
            }
            //Do checks, pay out the winner, end round
        }

        //Choose person to start, make 2 starting players pay big and small blind
            //Sort players in game.getPlayers() so they start at a specific person?
        //Drop players (probably from a new ArrayList like remainingPlayers) when they fold --DONE--
        //Keep track of money --DONE--
        //Check each player's hand by combining board and pocket to determine best hand ****
        //After first betting round, do flop --DONE--
        //Do another betting round, do turn --DONE--
        //Do another betting round, do river --DONE--
        //Do another betting round, show cards and determine winner of pot --HALF DONE--
        //Loop, resetting remainingPlayers to every player that still has money and increasing blinds --DONE--
        //Check at the beginning of a round if only one player has money, throw win condition
            //If Human is the last remaining player, allow them to add acronym to save high score
            //We need to figure out how or if we want to do score lmao
            //If human player is out, allow them to spectate or leave the game --MAYBE--

        //Return to main menu

    }

    public void BettingRound(Game game, UI ui) throws InterruptedException { //Loops through players, doing turns, then does flop
        //Reorder RemainingPlayers so dealer shifts positions
        //Force player in index 1 to pay
        game.setRound(game.getRound() + 1); //Adds 1 to the round
        for (int x = 0; x < game.getRemainingPlayers().size(); x++) {
//            Thread.sleep(3000);
            game.setPot(game.getPot() + game.getRemainingPlayers().get(x).turn(game, ui));
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
        for (int x = 0; x < game.getPlayers().size(); x++) {
            if (game.getPlayers().get(x).isSkipRound()) {
                game.getRemainingPlayers().remove(game.getPlayers().get(x));
            }
        }
    }

    public void RemovePlayers(Game game) { //Permanently removes players from the game if their money is 0
        for (int x = 0; x < game.getPlayers().size(); x++) {
            if (game.getPlayers().get(x).getMoney() <= 0 ) { //Should never be less than 0, but just in case
                game.getPlayers().remove(game.getPlayers().get(x));
            }
        }
    }
}
