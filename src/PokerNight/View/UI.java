package PokerNight.View;

import PokerNight.Model.AbsPlayer;
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
        if (game.getRound() == 4) { //Only display board, hands, etc. if the full round is played -- not if ended early from folds
            System.out.println("Board: " + game.getBoard());
            for (int x = 0; x < game.getRemainingPlayers().size(); x++) {
                if (!game.getRemainingPlayers().get(x).isOutOfGame() && !game.getRemainingPlayers().get(x).isSkipRound()) {
                    System.out.println(game.getRemainingPlayers().get(x).getName() + " had: " + game.getRemainingPlayers().get(x).getPocket()); //Add what their hand is
                }
            }
        }
        System.out.println(game.getWinner().getName() + " wins the round!\n");
    }

    public void printAction(String name, String action){
        System.out.println(name+" "+action+".\n");
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

    public void titleScreen(){
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
}
