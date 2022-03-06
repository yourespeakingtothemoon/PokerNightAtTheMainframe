package PokerNight.Model;

import PokerNight.View.UI;

import java.util.Random;

public class Beta extends AbsPlayer{
    @Override
    public int turn(Game game, UI ui) { //sigma grindset
        //#heboughtthebank
        Random rand = new Random(); //randomizer to decide what to do
        while (true) {
            switch (rand.nextInt(4)) {
                case 0: //Call
                    if (this.getMoney() >= game.getMinBet()) {
                        this.setMoney(this.getMoney() - game.getMinBet());
//                        sigmaStringset(rand.nextInt(10) + 1);
                        return game.getMinBet();
                    }
                    int returnAmt = this.getMoney();
                    this.setMoney(0);
//                    sigmaStringset(rand.nextInt(10 + 1));
                    return returnAmt;
                case 1: //raise
                    if (this.getMoney() >= game.getMinBet()) { //Disallows the Sigma to bet more than they have
                        int betAmt = rand.nextInt((this.getMoney() - game.getMinBet()) + 1) + game.getMinBet();
                        game.setMinBet(betAmt);
                        this.setMoney(this.getMoney() - game.getMinBet());
//                        sigmaStringset(rand.nextInt(10) + 1);
                        return betAmt;
                    }
                case 2: //fold
                    this.setSkipRound(true);
//                    sigmaStringset(rand.nextInt(10) + 1);
                    return 0;
                case 3: //Check
                    if (!(game.getRound() == 1)) {
                        return 0; //Stay in the game without betting
                    }
            }
        }
    }

    @Override
    public String toString() {
        return "Beta"; //For testing
    }
}
