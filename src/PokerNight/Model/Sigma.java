package PokerNight.Model;

import PokerNight.View.UI;

import java.util.Random;

public class Sigma extends AbsPlayer{
    //Constructor
    public Sigma(){

    }
    public Sigma(String name, int ID, String catchphrase){
        //add passed in values
    }
//turn abstract thingy

    //sigma grindset
    public void sigmaGrindset(boolean checkAllowed){
        //#heboughtthebank
        //randomizer to decide what to do
        if(!checkAllowed){
            Random rand = new Random();
            switch(rand.nextInt(2)+1){
                case 1:
                    //check
                    //check
                    sigmaStringset(rand.nextInt(10)+1);
                    break;
                case 2:
                    //raise
                    int betAmt = rand.nextInt(super.money);
                    //raise
                    sigmaStringset(rand.nextInt(10)+1);
                    break;
                case 3:
                    //fold
                    //fold
                    sigmaStringset(rand.nextInt(10)+1);
                    break;
            }
        }
    }

    private void sigmaStringset(int otherSpeak){
        Random rand = new Random();
        int ID;
        if(otherSpeak == rand.nextInt(9)+1){
          ID=rand.nextInt(9)+1;

        }else{
            ID = super.DialogueID;
        }
        //return string based on new ID and random number
        int stringVal = rand.nextInt(10)+1;
        // after completeing the dialog print class and methods, send in i.e. printSigmaDia(ID,stringVal)
    }

    @Override
    public int turn(Game game, UI ui) {
        return 0;
    }
}
