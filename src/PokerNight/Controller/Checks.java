package PokerNight.Controller;

import PokerNight.Model.Card;
import PokerNight.Model.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Checks {
    //Calculate win Probabiluiurghuiseruiasebeyfubfelfgniyugbyuif via finding if hand and board have certain win cond available
    public static int probScore(int turnNum, ArrayList<Card> hand, ArrayList<Card> board) {
        return 0;
    }

    //Detect methods
     //foundational
        private ArrayList<Card> ofAKind(ArrayList<Card> opnCard) {
        ArrayList<Card> OAKr = new ArrayList<>();
        ArrayList <ArrayList<Card>> OAKArray=new ArrayList<>();
        OAKArray.add(OAKr);
        for (int position = 0; position < opnCard.size(); position++){
            ArrayList<Card> OAK = new ArrayList<>();
            int ofA = 0;
            for (int pos = 0; pos < opnCard.size(); pos++) {

                if (opnCard.get(position).getRank() == opnCard.get(pos).getRank()) {
                    ofA++;
                }
            }
        if(ofA>=2){
            OAK.add(opnCard.get(position));
        }
        OAKArray.add(OAK);
        }

        for (int pos = 0; pos < OAKArray.size(); pos++) {
            if (OAKArray.get(pos).size() > OAKArray.get(pos).size()) {
                OAKr = OAKArray.get(pos);
            }
        }
        return OAKr;
    }

        private boolean sequence(ArrayList<Rank> opnCardRanks){
        Collections.sort(opnCardRanks);
        for(int pos=1; pos< opnCardRanks.size();pos++){
            if(opnCardRanks.get(pos-1).getNumVal()!=opnCardRanks.get(pos).getNumVal()-1){
                return false;
            }
        }
        return true;
    }

        private boolean sameSuit(ArrayList<Card> opnCard){
        for(int pos=1;pos< opnCard.size();pos++){
            if(opnCard.get(pos-1).getRank()!=opnCard.get(pos).getRank()){
                return false;
            }
        }
        return true;
    }

    //check boolean returns
        private int highCard(ArrayList<Rank> opnCardRanks){
        Collections.sort(opnCardRanks);
        if(opnCardRanks.get(0).getNumVal()==1){
            return 14;
        }else{
            int highestIndex = opnCardRanks.size();
            return opnCardRanks.get(highestIndex).getNumVal();
        }
        }
        private String OAKofAKind(ArrayList<Card> opnCard){

        //plan: get two arraylists determine if its two pairs,
            // a full house or a single of a kind and send back an String value based on that
            return null;
    }
        private boolean


}







