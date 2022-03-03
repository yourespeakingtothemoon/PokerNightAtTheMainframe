package PokerNight.Controller;

import PokerNight.Model.Card;
import PokerNight.Model.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Checks {
    //Calculate win Probability via finding if hand and board have certain win cond available
    public static int probScore(int turnNum, ArrayList<Card> hand, ArrayList<Card> board) {
        switch(turnNum){
            //nested switch lets go      :gun emoji: :my head emoji:
        }
        return 0;
    }
//Detect methods
    //foundational
    private ArrayList<Card> ofAKind(ArrayList<Card> opnCard) {
        ArrayList<Card> OAKr = new ArrayList<>();
        ArrayList<ArrayList<Card>> OAKArray = new ArrayList<>();
        OAKArray.add(OAKr);
        for (int position = 0; position < opnCard.size(); position++) {
            ArrayList<Card> OAK = new ArrayList<>();
            int ofA = 0;
            for (int pos = 0; pos < opnCard.size(); pos++) {

                if (opnCard.get(position).getRank() == opnCard.get(pos).getRank()) {
                    ofA++;
                }
            }
            if (ofA >= 2) {
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

    private boolean sequence(ArrayList<Rank> opnCardRanks) {
        Collections.sort(opnCardRanks);
        for (int pos = 1; pos < opnCardRanks.size(); pos++) {
            if (opnCardRanks.get(pos - 1).getNumVal() != opnCardRanks.get(pos).getNumVal() - 1) {
                return false;
            }
        }
        return true;
    }

    private boolean sameSuit(ArrayList<Card> opnCard) {
        for (int pos = 1; pos < opnCard.size(); pos++) {
            if (opnCard.get(pos - 1).getRank() != opnCard.get(pos).getRank()) {
                return false;
            }
        }
        return true;
    }

    //check boolean returns
    private int highCard(ArrayList<Rank> opnCardRanks) {
        Collections.sort(opnCardRanks);
        if (opnCardRanks.get(0).getNumVal() == 1) {
            return 14;
        } else {
            int highestIndex = opnCardRanks.size()-1;
            return opnCardRanks.get(highestIndex).getNumVal();
        }
    }

    private String OAKofAKind(ArrayList<Card> opnCard) {
        //first of a kind (OAK) set to find
        ArrayList<Card> oakOne = new ArrayList<>();
        oakOne = ofAKind(opnCard);
        //remove intersection of oakOne and the master set of open cards
        for (int pos = 0; pos < opnCard.size(); pos++) {
            for (int posit = 0; pos < oakOne.size(); posit++) {
                if (oakOne.get(posit) == opnCard.get(pos)) {
                    opnCard.remove(pos);
                    break;
                }
            }
        }
        //second of a kind set
        ArrayList<Card> oakTwo = new ArrayList<>();
        //only do if the first actually produced a set
        if (opnCard.size() >= 2) {
            oakTwo = ofAKind(opnCard);

        }
        //make union of oakOne and oakTwo
        ArrayList<Card> oakR = new ArrayList<>();

        oakR.addAll(oakOne);
        oakR.addAll(oakTwo);
        //use "return" version of card set (OAKr)'s size to determine what kind of "of a kind" is on the board and in the pocket of the player inquiring
        switch (oakR.size()) {
            case 2:
                return "pair";
            case 4:
                if (oakOne.size() == 2 && oakTwo.size() == 2) {
                    return "two pairs";
                } else {
                    return "fourOAK";
                }
            case 5:
                return "full house";
            default:
                return "none";
        }
        //plan: get two arraylists determine if its two pairs,
        // a full house or a single of a kind and send back an String value based on that
    }

    private String straightFlush(ArrayList<Card> opnCard) {
        //check for sequence if straight return "straight"
        //make list of ranks from the open cards
        ArrayList<Rank> ranks = new ArrayList<>();
        for (int pos = 0; pos < opnCard.size(); pos++) {
            //add rank to list
            ranks.add(opnCard.get(pos).getRank());
        }
        boolean seq = sequence(ranks);
        //check for same suit if straightFlush return "straightFlush"
        boolean flush = sameSuit(opnCard);
        //if sequence and same suit check pass, then check lowest card value post sort, if 10, return "royalFlush"
        Collections.sort(ranks);
        if (seq && flush && ranks.get(0).getNumVal() == 10) {
            return "royalFlush";
        }
        if (seq && flush) {
            return "straightFlush";
        }
        if (flush) {
            return "flush";
        } else {
            return "straight";
        }
    }


}







