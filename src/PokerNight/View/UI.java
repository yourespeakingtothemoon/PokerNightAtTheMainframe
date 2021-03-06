package PokerNight.View;

import PokerNight.Controller.Checks;
import PokerNight.Model.Players.AbsPlayer;
import PokerNight.Model.Game;
import lib.ConsoleIO;

public class UI {

    public void DisplayGame(Game game) { //Display all players' money, board, player hand, pot, blinds, etc.
        for (int x = 0; x < game.getPlayers().size(); x++) {
            System.out.print(game.getPlayers().get(x).getName() + ": " + game.getPlayers().get(x).getMoney() + "\t\t");
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

    public void DisplayEndRound(Game game) { //Displays the end of a game -- the hands of every player still in and the winner
        System.out.println("Board: " + game.getBoard());
        for (int x = 0; x < game.getRemainingPlayers().size(); x++) {
            if (!game.getRemainingPlayers().get(x).isOutOfGame() && !game.getRemainingPlayers().get(x).isSkipRound()) {
                System.out.println(game.getRemainingPlayers().get(x).getName() + " had: " + game.getRemainingPlayers().get(x).getPocket() +
                        " (" + handName(Checks.probScore(5, game.getRemainingPlayers().get(x).getPocket(), game.getBoard())) + ")"); //Add what their hand is
            }
        }
        if (game.getWinners().size() == 1) {
            System.out.println(game.getWinners().get(0).getName() + " wins the round!\n");
        } else {
            StringBuilder winnerList = new StringBuilder();
            for (int pos = 0; pos < game.getWinners().size(); pos++) {
                if (pos == 0) {
                    winnerList.append(game.getWinners().get(pos).getName());
                } else {
                    winnerList.append(" and " + game.getWinners().get(pos).getName());
                }
            }
            if (game.getWinners().size() == 2) {
                System.out.println(winnerList + " both win the round, and split the pot.\n");
            } else {
                System.out.println(winnerList + " all win the round, and split the pot.\n");
            }

        }
    }

    public void printAction(String name, String action) {
        System.out.println(name + " " + action + ".\n");
    }

    public void printError() {
        System.out.println("You don't have enough money to do that!");
    }

    public void DisplayWinner(AbsPlayer player) {
        System.out.println(player.getName() + " wins the tournament!");
    }

    public int GetInt(String prompt, int min, int max) {
        return ConsoleIO.promptForInt(prompt, min, max);
    }

    public void titleScreen() {
        System.out.println("                 ____        __                         __  __              __      __            ");
        System.out.println("                /\\  _`\\     /\\ \\                       /\\ \\/\\ \\  __        /\\ \\    /\\ \\__          ");
        System.out.println("                \\ \\ \\L\\ \\___\\ \\ \\/'\\      __   _ __    \\ \\ `\\\\ \\/\\_\\     __\\ \\ \\___\\ \\ ,_\\       ");
        System.out.println("                 \\ \\ ,__/ __`\\ \\ , <    /'__`\\/\\`'__\\   \\ \\ , ` \\/\\ \\  /'_ `\\ \\  _ `\\ \\ \\/         ");
        System.out.println("                  \\ \\ \\/\\ \\L\\ \\ \\ \\\\`\\ /\\  __/\\ \\ \\/     \\ \\ \\`\\ \\ \\ \\/\\ \\L\\ \\ \\ \\ \\ \\ \\ \\_        ");
        System.out.println("                   \\ \\_\\ \\____/\\ \\_\\ \\_\\ \\____\\\\ \\_\\      \\ \\_\\ \\_\\ \\_\\ \\____ \\ \\_\\ \\_\\ \\__\\       ");
        System.out.println("                    \\/_/\\/___/  \\/_/\\/_/\\/____/ \\/_/       \\/_/\\/_/\\/_/\\/___L\\ \\/_/\\/_/\\/__/       ");
        System.out.println("                                                                         /\\____/                   ");
        System.out.println("                                                                         \\_/__/                    ");

        System.out.println("              __        __    __                                                    ___                                     ");
        System.out.println("             /\\ \\__    /\\ \\__/\\ \\                  /'\\_/`\\            __          /'___\\                                    ");
        System.out.println("         __  \\ \\ ,_\\   \\ \\ ,_\\ \\ \\___      __     /\\      \\     __   /\\_\\    ___ /\\ \\__/  _ __    __      ___ ___      __   ");
        System.out.println("       /'__`\\ \\ \\ \\/    \\ \\ \\/\\ \\  _ `\\  /'__`\\   \\ \\ \\__\\ \\  /'__`\\ \\/\\ \\ /' _ `\\ \\ ,__\\/\\`'__\\/'__`\\  /' __` __`\\  /'__`\\ ");
        System.out.println("      /\\ \\L\\.\\_\\ \\ \\_    \\ \\ \\_\\ \\ \\ \\ \\/\\  __/    \\ \\ \\_/\\ \\/\\ \\L\\.\\_\\ \\ \\/\\ \\/\\ \\ \\ \\_/\\ \\ \\//\\ \\L\\.\\_/\\ \\/\\ \\/\\ \\/\\  __/ ");
        System.out.println("      \\ \\__/.\\_\\\\ \\__\\    \\ \\__\\\\ \\_\\ \\_\\ \\____\\    \\ \\_\\\\ \\_\\ \\__/.\\_\\\\ \\_\\ \\_\\ \\_\\ \\_\\  \\ \\_\\\\ \\__/.\\_\\ \\_\\ \\_\\ \\_\\ \\____\\");
        System.out.println("       \\/__/\\/_/ \\/__/     \\/__/ \\/_/\\/_/\\/____/     \\/_/ \\/_/\\/__/\\/_/ \\/_/\\/_/\\/_/\\/_/   \\/_/ \\/__/\\/_/\\/_/\\/_/\\/_/\\/____/");


    }

    private String handName(int idx) {
        switch (idx) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return String.valueOf(idx + 1) + " High";
            case 10:
                return "Jack High";
            case 11:
                return "Queen High";
            case 12:
                return "King High";
            case 13:
                return "Ace High";
            case 14:
                return "Pair";
            case 15:
                return "Two Pairs";
            case 16:
                return "Three of a Kind";
            case 18:
                return "Straight";
            case 20:
                return "Flush";
            case 22:
                return "Full House";
            case 24:
                return "Four of a Kind";
            case 26:
                return "Straight Flush";
            case 28:
                return "Royal Flush!";
            default:
                return "nothing!";
        }
    }
}
