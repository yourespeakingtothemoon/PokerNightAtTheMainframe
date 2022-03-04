package PokerNight.Model;

import PokerNight.View.UI;
import java.util.Random;

public class Sigma extends AbsPlayer {
    //Constructor
    public Sigma() {

    }

    public Sigma(String name, int ID, String catchphrase) {
        //add passed in values
    }

//turn abstract thingy -- This is pretty much how it should work for all archetypes
//Main difference is the way they determine their action
    @Override
    public int turn(Game game, UI ui) { //sigma grindset
        System.out.println("Sigma has " + this.getMoney());
        //#heboughtthebank
        Random rand = new Random(); //randomizer to decide what to do
        while (true) {
            switch (rand.nextInt(2) + 1) {
                case 1: //Call
                    if (this.getMoney() >= game.getMinBet()) {
                        this.setMoney(this.getMoney() - game.getMinBet());
                        sigmaStringset(rand.nextInt(10) + 1);
                        return game.getMinBet();
                    }
                    int returnAmt = this.getMoney();
                    this.setMoney(0);
                    sigmaStringset(rand.nextInt(10 + 1));
                    return returnAmt;
                case 2: //raise
                    if (this.getMoney() >= game.getMinBet()) { //Disallows the Sigma to bet more than they have
                        int betAmt = rand.nextInt((this.getMoney() - game.getMinBet()) + 1) + game.getMinBet();
                        game.setMinBet(betAmt);
                        sigmaStringset(rand.nextInt(10) + 1);
                        return betAmt;
                    }
                case 3: //fold
                    this.setSkipRound(true);
                    sigmaStringset(rand.nextInt(10) + 1);
                    return 0;
                case 4: //Check
                    if (!(game.getRound() == 1)) {
                        return 0; //Stay in the game without betting
                    }
            }
        }
    }

    private void sigmaStringset(int otherSpeak) { //Still needs to be implemented
        Random rand = new Random();
        int ID;
        if (otherSpeak == rand.nextInt(9) + 1) {
            ID = rand.nextInt(9) + 1;

        } else {
            ID = super.DialogueID;
        }
        //return string based on new ID and random number
        int stringVal = rand.nextInt(10) + 1;
        // after completing the dialog print class and methods, send in i.e. printSigmaDia(ID,stringVal)
    }
}
