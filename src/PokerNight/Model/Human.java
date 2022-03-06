package PokerNight.Model;

import PokerNight.View.UI;
import lib.ConsoleIO;

import java.util.ArrayList;

public class Human extends AbsPlayer {

    public Human() {
        this.name = "The Player";
        this.money = 0;
        this.pocket = new ArrayList<>();
        this.skipRound = false;
    }

    @Override
    public int turn(Game game, UI ui) { //Might return the amount the player bets instead of nothing
        while (true) {
            String prompt = "a: Call\nb: Bet\nc: Fold\n";
            if (!(game.getRound() == 1)) {
                prompt += "d: Check\n";
            }
            switch(ConsoleIO.promptForString(prompt, false)) {
                case "a": //Call
                    //Stay in the game, equal the bet of the previous player
                    this.setMoney(this.getMoney() - game.getMinBet());
                    return game.getMinBet();
                case "b": //Bet
                    //Bet an amount that is set as the new minBet
                    game.setMinBet(ui.GetInt("How much would you like to bet?\n", game.getMinBet() * 2, this.getMoney() + game.getMinBet())); //No smaller than min bet, no larger than player money
                    this.setMoney(this.getMoney() - game.getMinBet());
                    return game.getMinBet(); //Hopefully sets minBet to whatever the player bets -- NEEDS TESTING --
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
