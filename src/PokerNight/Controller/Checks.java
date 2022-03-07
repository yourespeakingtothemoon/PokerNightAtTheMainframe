package PokerNight.Controller;

import PokerNight.Model.Card;
import PokerNight.Model.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Checks {
    //Calculate win Probability via finding if hand and board have certain win cond available
    public static int probScore(int turnNum, ArrayList<Card> pocket, ArrayList<Card> board) {
        ArrayList<Card> openCard = new ArrayList<>();
        openCard.addAll(pocket);
        openCard.addAll(board);
        switch(turnNum){
            //pre-flop
            case 0:
                return probScoreCalc(13,0,13,13,0,0,0,0,pocket);
            //flop
            case 1:
                //turn/fourth street
            case 2:
                //final betting round before showdown
            case 3:
                return probScoreCalc(13,16,19,22,24,27,30,33,openCard);
            //winner determination case
            case 4:
                return probScoreCalc(14,16,18,20,22,24,26,28,openCard);
                //this dont need be but hey incase error
            default:
                return 0;
        }
    }
//method that pulls valuations for every check that turn. and returns their sum to the main probScore method
    private static int probScoreCalc( int pairVal, int threeOAKVal, int straightVal, int flushVal, int fullHouseVal, int fourOAKVal, int strFlushVal, int royalVal, ArrayList<Card> opnCards){
        int probabilityPoints=highCard(ranksList(opnCards));
        //add points for Of a Kinds
        switch(OAKofAKind(opnCards)){
            case "pair":
                probabilityPoints=pairVal;
                break;
            case "two pairs":
                probabilityPoints=pairVal+1;
                break;
            case"threeOAK":
                probabilityPoints=threeOAKVal;
                break;
            case "fourOAK":
                probabilityPoints=fourOAKVal;
                break;
            case "full house":
                probabilityPoints=fullHouseVal;
                break;
            case"none":
            default:
                break;
        }
        //add points for sequences and flushes
        switch(straightFlush(opnCards)){
            case "straight":
                probabilityPoints=straightVal;
                break;
            case "flush":
                probabilityPoints=flushVal;
                break;
            case "straightFlush":
                probabilityPoints=strFlushVal;
                break;
            case "royalFlush":
                probabilityPoints=royalVal;
                break;
            default:
                break;
        }
        return probabilityPoints;
    }
//pair tie
public static String tieBreak(String name1, ArrayList<Card> hand1, String name2, ArrayList<Card> hand2){
        if(highCard(ranksList(ofAKind(hand1))) > highCard(ranksList(ofAKind(hand2)))){
            return name1;
    }
    if(highCard(ranksList(ofAKind(hand1))) < highCard(ranksList(ofAKind(hand2)))){
        return name2;
    }else{
        return "tie";
    }
}
    //Detect methods
    //foundational
    private static ArrayList<Card> ofAKind(ArrayList<Card> opnCard) {
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

    private static boolean sequence(ArrayList<Rank> opnCardRanks) {
        Collections.sort(opnCardRanks);
        for (int pos = 1; pos < opnCardRanks.size(); pos++) {
            if (opnCardRanks.get(pos - 1).getNumVal() != opnCardRanks.get(pos).getNumVal() - 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean sameSuit(ArrayList<Card> opnCard) {
        for (int pos = 1; pos < opnCard.size(); pos++) {
            if (opnCard.get(pos - 1).getRank() != opnCard.get(pos).getRank()) {
                return false;
            }
        }
        return true;
    }

    //check boolean returns
    private static int highCard(ArrayList<Rank> opnCardRanks) {
        Collections.sort(opnCardRanks);
        if (opnCardRanks.get(0).getNumVal() == 1) {
            return 14;
        } else {
            int highestIndex = opnCardRanks.size()-1;
            return opnCardRanks.get(highestIndex).getNumVal();
        }
    }

    private static String OAKofAKind(ArrayList<Card> opnCard) {
        //first of a kind (OAK) set to find
        ArrayList<Card> oakOne;
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
            case 3:
                return "threeOAK";
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

    private static String straightFlush(ArrayList<Card> opnCard) {
        //check for sequence if straight return "straight"
        ArrayList<Rank> ranks = ranksList(opnCard);
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

    //other stuff
    private static ArrayList<Rank> ranksList(ArrayList<Card> opnCard){
        //make list of ranks from the open cards
        ArrayList<Rank> ranks = new ArrayList<>();
        for (int pos = 0; pos < opnCard.size(); pos++) {
            //add rank to list
            ranks.add(opnCard.get(pos).getRank());
        }
        return ranks;
    }

}







