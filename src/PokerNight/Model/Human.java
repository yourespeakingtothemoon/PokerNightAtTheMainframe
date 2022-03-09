package PokerNight.Model;

import PokerNight.View.UI;
import lib.ConsoleIO;

import java.util.ArrayList;

public class Human extends AbsPlayer {

    public Human() {
        this.name = ConsoleIO.promptForString("Please enter your name!\n",false);
        this.money = 0;
        this.pocket = new ArrayList<>();
        this.skipRound = false;
    }

    @Override
    public int turn(Game game, UI ui) {
        while (true) {
            String prompt = "a: Call\nb: Bet\nc: Fold\n";
            if (!(game.getRound() == 1)) {
                prompt += "d: Check\n";
            }
            switch(ConsoleIO.promptForString(prompt, false)) {
                case "a": //Call
                    //Stay in the game, equal the bet of the previous player
                    if (this.getMoney() >= game.getMinBet()) {
                        this.setMoney(this.getMoney() - game.getMinBet());
                        return game.getMinBet();
                    }
                    int returnAmt = this.getMoney(); //If the player doesn't have enough to call, they just put in their max amount.
                    this.setMoney(0);
                    return returnAmt;
                case "b": //Bet
                    //Bet an amount that is set as the new minBet
                    if (this.getMoney() < game.getMinBet()) {
                        ui.printError(); //You don't have enough money for that!
                        break;
                    }
                    game.setMinBet(ui.GetInt("How much would you like to bet?\n", game.getMinBet(), this.getMoney())); //No smaller than min bet, no larger than player money
                    this.setMoney(this.getMoney() - game.getMinBet());
                    return game.getMinBet(); //Sets minBet to whatever the player bets
                case "c": //Fold
                    // Player skips round, is skipped for remaining turns
                    // Allow player to skip to next round -- OPTIONAL
                    this.setSkipRound(true);
                    return 0;
                case "d": //Check
                    if (!(game.getRound() == 1)) { //Only possible after flop
                        return 0; //Stay in game without betting
                    }
            }
        }
    }
}
