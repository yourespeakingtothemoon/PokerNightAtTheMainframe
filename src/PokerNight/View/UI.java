package PokerNight.View;

import PokerNight.Model.AbsPlayer;
import PokerNight.Model.Game;
import lib.ConsoleIO;

public class UI {

    public void DisplayGame(Game game) { //Display board, player hand, pot, blinds, etc.
        System.out.print("Board: ");
        if (game.getBoard() != null) {
            System.out.print(game.getBoard());
        }
        System.out.print("\t Your hand: " + game.getPlayers().get(0).getPocket() + "\t");
        System.out.print("Your money: " + game.getPlayers().get(0).getMoney() + "\t");
        System.out.print("Current pot: " + game.getPot() + "\t");
        System.out.print("Minimum bet: " + game.getMinBet() + "\n");
    }

    public void DisplayHand(AbsPlayer player) {
        System.out.println(player.getPocket());
    }

    public String GetString(String prompt) {
        return ConsoleIO.promptForString(prompt, false);
    }

    public int GetInt(String prompt, int min, int max) {
        return ConsoleIO.promptForInt(prompt, min, max);
    }
}
