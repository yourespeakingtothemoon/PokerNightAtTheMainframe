package PokerNight.Model;

import PokerNight.View.UI;
import lib.ConsoleIO;

import java.util.ArrayList;

public class Human extends AbsPlayer {

    public Human() {
        this.money = 0;
        this.pocket = new ArrayList<>();
        this.skipRound = false;
    }

    @Override
    public int turn(ArrayList<Card> board, int minBet, ArrayList<Card> gameDeck, UI ui) { //Might return the amount the player bets instead of nothing
        while (true) {
            switch(ConsoleIO.promptForString("a: Call\n" +
                    "b: Bet\n" +
                    "c: Fold\n" +
                    "d: Check\n", false)) {
                case "a": //Call
                    //Stay in the game, equal the bet of the previous player
                    this.setMoney(this.getMoney() - minBet);
                    return minBet;
                case "b": //Bet
                    //Bet an amount that is set as the new minBet
                    minBet = ui.GetInt("How much would you like to bet?\n", minBet * 2, this.getMoney()); //No smaller than min bet, no larger than player money
                    this.setMoney(this.getMoney() - minBet);
                    return minBet; //Hopefully sets minBet to whatever the player bets -- NEEDS TESTING --
                case "c": //Fold
                    // Player skips round, is skipped for remaining turns
                    // Allow player to skip to next round -- OPTIONAL
                    this.setSkipRound(true);
                    return 0;
                case "d": //Check
                    //Only possible after flop... Somehow only show this option after betting round 1?
                    //Stay in game without betting
                    return 0;
            }
        }
    }
}
