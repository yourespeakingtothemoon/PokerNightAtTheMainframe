package PokerNight.Controller;

import PokerNight.DAL.DAPoker;
import PokerNight.Model.*;
import PokerNight.View.UI;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GamePlay {

    public void StartGame() throws IOException, ParseException {
        Game game = new Game();
        UI ui = new UI();
        Random rand = new Random();
        //Select players (one bot of each archetype) as well as the Human player
        game.getPlayers().add(new Human());
        game.getPlayers().add(DAPoker.playerPull("alpha",(rand.nextInt(3)+1)));
        game.getPlayers().add(DAPoker.playerPull("beta",(rand.nextInt(3)+1)));
        game.getPlayers().add(DAPoker.playerPull("sigma",(rand.nextInt(3)+1)));
        game.getPlayers().add(DAPoker.playerPull("omega",(rand.nextInt(3)+1)));

        MainGamePlayLoop(game, ui);
    }

    public void MainGamePlayLoop(Game game, UI ui) throws IOException, ParseException {
        for (int x = 0; x < game.getPlayers().size(); x++) { //Give each player an empty hand, $10k to bet
            game.getPlayers().get(x).setPocket(new ArrayList<>()); //this could be added to a constructor instead for new AbsPlayers
            game.getPlayers().get(x).setMoney(10000);
        }

        while (true) {
                //If they're human, save high score, etc.
            RemovePlayers(game); //Permanent removal of players

            for (int i = 0; i < game.getPlayers().size(); i++) {
                if (game.getPlayers().get(i).getMoney() >= 50000) { //If one player has all money=
                    ui.DisplayWinner(game.getPlayers().get(i));
                    return;
                }
            }

            game.NewRound(); //Resets just about everything
            AnteUp(game, ui); //Forces every player to bet money before the round

            for (int x = 0; x < game.getRemainingPlayers().size(); x++) { //Give each player 2 (pocket) cards
                game.getRemainingPlayers().get(x).getPocket().clear();
                if (!game.getRemainingPlayers().get(x).isOutOfGame()) {
                    game.getRemainingPlayers().get(x).setPocket(new ArrayList<>(DrawCard(2, game.getGameDeck())));
                    game.getRemainingPlayers().get(x).setSkipRound(false);
                }
            }

            ui.DisplayGame(game);
            for (int x = 0; x < 4; x++) {
                BettingRound(game, ui);
                SetRemainingPlayers(game);
                ui.DisplayGame(game);
            }
            //Do checks, pay out the winner, end round
            int winningScore=0;
            for (int y = 0; y < game.getPlayers().size(); y++) { //Go through all players
                System.out.println(Checks.probScore(5,game.getPlayers().get(y).getPocket(),game.getBoard())); //For testing
                if (!game.getPlayers().get(y).isOutOfGame()) { //For each player that isn't out...
                    if(Checks.probScore(5,game.getPlayers().get(y).getPocket(),game.getBoard())>winningScore){
                        game.setWinner(game.getPlayers().get(y));
                        winningScore=Checks.probScore(5,game.getWinner().getPocket(),game.getBoard());
                    }
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

    public void BettingRound(Game game, UI ui) throws IOException, ParseException { //Loops through players, doing turns, then adds cards to the board
        game.setRound(game.getRound() + 1); //Adds 1 to the round
        if (game.getRemainingPlayers().size() <= 1) { //Quickly ends the round if one player wins
            return;
        }
        for (int x = 0; x < game.getRemainingPlayers().size(); x++) {
            if (SetRemainingPlayers(game)) { //Sets remaining players before each player's turn
                x = Math.max(x - 1, 0);
            }
            if (game.getRemainingPlayers().size() <= 1) { //Quickly ends the round if one player wins
                return;
            }
            if (!game.getRemainingPlayers().get(x).isOutOfGame()) {
                game.setPot(game.getPot() + game.getRemainingPlayers().get(x).turn(game, ui));
            }
        }
        if (game.getRound() == 1) { //If it's round 1, do the Flop
            game.getMuck().add(DrawCard(1, game.getGameDeck()).get(0)); //Throws one card out, as per poker rules
            game.setBoard(DrawCard(3, game.getGameDeck())); //The Flop
        } else if (game.getRound() < 4) { //If it's any other round besides the final betting round, only add 1 card
            game.getMuck().add(DrawCard(1, game.getGameDeck()).get(0));
            game.getBoard().add(DrawCard(1, game.getGameDeck()).get(0)); //Draws 1 card, adds it to the board -- River or Turn
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
 public void AnteUp(Game game, UI ui) {
        for (int x = 0; x < game.getPlayers().size(); x++) {
            if (!game.getPlayers().get(x).isOutOfGame()) {
                int anteAmount = Math.min(game.getMinBet(), game.getPlayers().get(x).getMoney());
                game.setPot(game.getPot() + anteAmount);
                game.getPlayers().get(x).setMoney(game.getPlayers().get(x).getMoney() - anteAmount);
            }
        }
        ui.printAction("Everyone", "antes up");
    }

    //May move these to the Game class
    public boolean SetRemainingPlayers(Game game) { //Sets the remaining players for the TURN, not the game
        for (int x = 0; x < game.getRemainingPlayers().size(); x++) {
            if (game.getRemainingPlayers().get(x).isSkipRound()) {
                game.getRemainingPlayers().remove(x);
                return true;
            }
        }
        return false;
    }

    public void RemovePlayers(Game game) { //Permanently removes players from the game if their money is 0
        for (int x = 0; x < game.getPlayers().size(); x++) {
            if (game.getPlayers().get(x).getMoney() <= 0 ) { //Should never be less than 0, but just in case
                game.getPlayers().get(x).setOutOfGame(true);
            }
        }
    }
}
