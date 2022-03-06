package PokerNight.View;

import PokerNight.Model.AbsPlayer;
import PokerNight.Model.Game;
import lib.ConsoleIO;

public class UI {

    public void DisplayGame(Game game) { //Display all players' money, board, player hand, pot, blinds, etc.
        for (int x = 0; x < game.getPlayers().size(); x++) {
            System.out.print(game.getPlayers().get(x) + ": " + game.getPlayers().get(x).getMoney() + game.getPlayers().get(x).isSkipRound() + game.getPlayers().get(x).isOutOfGame() + "\t");
        } //Needs testing
        System.out.print("\nBoard: ");
        if (game.getBoard() != null) {
            System.out.print(game.getBoard());
        }
        System.out.print("\t Your hand: " + game.getPlayers().get(0).getPocket() + "\t");
        System.out.print("Your money: " + game.getPlayers().get(0).getMoney() + "\t");
        System.out.print("Current pot: " + game.getPot() + "\t");
        System.out.print("Minimum bet: " + game.getMinBet() + "\n \n");
    }

    public void DisplayEndRound(Game game) {
        System.out.println("Board: " + game.getBoard());
        for (int x = 0; x < game.getRemainingPlayers().size(); x++) {
            System.out.println(game.getRemainingPlayers().get(x).getName() + " had: " + game.getRemainingPlayers().get(x).getPocket());
        }
        System.out.println(game.getWinner().getName() + " wins the round!\n");
    }

    public void DisplayWinner(AbsPlayer player) {
        System.out.println(player.getName() + " wins the tournament!");
    }

    public int GetInt(String prompt, int min, int max) {
        return ConsoleIO.promptForInt(prompt, min, max);
    }
}
