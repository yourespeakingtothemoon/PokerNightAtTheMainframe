package PokerNight.Controller;

import PokerNight.Model.*;
import PokerNight.Model.Cards.Card;
import PokerNight.Model.Cards.Rank;
import PokerNight.Model.Cards.Suit;
import PokerNight.Model.Players.AbsPlayer;

import java.util.*;

import static PokerNight.Model.Cards.Rank.*;

public class Checks {
    //Calculate win Probability via finding if hand and board have certain win cond available
    public static int probScore(int turnNum, ArrayList<Card> pocket, ArrayList<Card> board) {
        ArrayList<Card> openCard = new ArrayList<>();
        openCard.addAll(pocket);
        openCard.addAll(board);
        switch (turnNum) {
            //pre-flop
            case 1:
                return probScoreCalc(13, 13, 13, 13, 16, 16, 16, 20, pocket, pocket);
            //flop
            case 2:
                //turn/fourth street
            case 3:
                //final betting round before showdown
            case 4:
                return probScoreCalc(13, 16, 19, 22, 24, 27, 30, 33, openCard, pocket);
            //winner determination case
            case 5:
                return probScoreCalc(14, 16, 18, 20, 22, 24, 26, 28, openCard, pocket);
            //default case, should normally never be reached, but if it is, return 0
            default:
                return 0;
        }
    }

    //method that pulls valuations for every check that turn. and returns their sum to the main probScore method
    private static int probScoreCalc(int pairVal, int threeOAKVal, int straightVal, int flushVal, int fullHouseVal, int fourOAKVal, int strFlushVal, int royalVal, ArrayList<Card> opnCards, ArrayList<Card> pocket) {
        int probabilityPoints = highCard(pocket);
        //add points for Of a Kinds
        switch (OAKofAKind(opnCards)) {
            case "pair":
                probabilityPoints = pairVal;
                break;
            case "two pairs":
                probabilityPoints = pairVal + 1;
                break;
            case "threeOAK":
                probabilityPoints = threeOAKVal;
                break;
            case "fourOAK":
                probabilityPoints = fourOAKVal;
                break;
            case "full house":
                probabilityPoints = fullHouseVal;
                break;
            case "none":
            default:
                break;
        }
        //add points for sequences and flushes
        switch (straightFlush(opnCards)) {
            case "straight":
                probabilityPoints = straightVal;
                break;
            case "flush":
                probabilityPoints = flushVal;
                break;
            case "straightFlush":
                probabilityPoints = strFlushVal;
                break;
            case "royalFlush":
                probabilityPoints = royalVal;
                break;
            case "none":
            default:
                break;
        }
        return probabilityPoints;
    }

    //ties
    //ties - check for high card
    public static ArrayList<AbsPlayer> tieBreakHigh(ArrayList<AbsPlayer> players) {
        ArrayList<AbsPlayer> winners = new ArrayList<>();
        int highestCard = 0;
        for (int pos = 0; pos < players.size(); pos++) {
            int value = highCard(players.get(pos).getPocket());
            if (value > highestCard) {
                highestCard = value;
            }
        }
        //add winners to list
        for (int pos = 0; pos < players.size(); pos++) {
            // int value = highCard(players.get(pos).getPocket());
            if (highCard(players.get(pos).getPocket()) == highestCard) {
                winners.add(players.get(pos));
            }
        }
        return winners;
//      //check for ties and add additonal winner(s)
//       for(int pos=0; pos<players.size();pos++){
//           int value = highCard(players.get(pos).getPocket());
//           int winValue = highCard(winners.get(0).getPocket());
//           if(value==winValue){
//              winners.add(players.get(pos));
//           }
//       }


    }

    //ties - check for best pair
    public static ArrayList<AbsPlayer> tieBreakPair(ArrayList<AbsPlayer> players, Game game) {
        ArrayList<AbsPlayer> winners = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();
        //find unique pair yes this is insane
        for (int pos = 0; pos < players.size(); pos++) {
            ArrayList<Integer> thisPair = new ArrayList<>();
            for (int position = 0; position < players.get(pos).getPocket().size(); position++) {
                for (int posit = 0; posit < game.getBoard().size(); posit++) {
                    if (players.get(pos).getPocket().get(position) == game.getBoard().get(posit)) {
                        winners.add(players.get(pos));
                        thisPair.add(players.get(pos).getPocket().get(position).getRank().getNumVal());
                        thisPair.add(game.getBoard().get(posit).getRank().getNumVal());
                        pairs.add(thisPair);
                        break;
                    }
                }
            }
        }

//        //eliminate any that are too low
//        ArrayList<Integer> pairCards= new ArrayList<>();
//        for(int pos=0;pos<pairs.size();pos++){
//            pairCards.add(pairs.get(pos).get(0));
//        }
        if (winners.size() != 0) {
            //find best winners
            ArrayList<AbsPlayer> returnWinner = new ArrayList<>();
            int bestIdx = 0;
            for (int pos = 0; pos < winners.size(); pos++) {
                if (pairs.get(pos).get(0) >= bestIdx) {
                    returnWinner.add(winners.get(pos));
                }
            }
            //return winners
            return returnWinner;

        } else {
            return tieBreakHigh(players);
        }
    }

    //Detect methods
    //foundational
    private static ArrayList<Card> ofAKind(ArrayList<Card> opnCard) {
        ArrayList<Card> OAKr = new ArrayList<>();
        // ArrayList<ArrayList<Card>> OAKArray = new ArrayList<>();
        //OAKArray.add(OAKr);
        for (int position = 0; position < opnCard.size(); position++) {
            ArrayList<Card> OAK = new ArrayList<>();
            for (int pos = 0; pos < opnCard.size(); pos++) {
                // OAK.add(opnCard.get(position));
                if (opnCard.get(position).getRank() == opnCard.get(pos).getRank()) {
                    OAK.add(opnCard.get(pos));
                }
            }

            if (OAK.size() > OAKr.size()) {
                OAKr = OAK;
            }
        }
        return OAKr;
    }

    private static boolean sequence(ArrayList<Rank> opnCardRanks) {
        Collections.sort(opnCardRanks);
        int size = Math.min(opnCardRanks.size(), 5);
        int sequenceCount = 0;
        for (int pos = 1; pos < opnCardRanks.size(); pos++) {
            if (opnCardRanks.get(pos - 1).getNumVal() + 1 == opnCardRanks.get(pos).getNumVal()) {
                sequenceCount++;
                // for testing only System.out.println("sequence add!");
            }
        }
        return sequenceCount >= size;
    }

    private static boolean sameSuit(ArrayList<Card> opnCard) {
        int size = Math.min(opnCard.size(), 5);
        //intialize counts for each suit
        int returnCount = 0;
        int spadeCount = 0;
        int clubCount = 0;
        int heartCount = 0;
        int diamondCount = 0;
        for (int pos = 0; pos < opnCard.size(); pos++) {
            if (opnCard.get(pos).getSuit() == Suit.SPADES) {
                spadeCount++;
            }
            if (opnCard.get(pos).getSuit() == Suit.CLUBS) {
                clubCount++;
            }
            if (opnCard.get(pos).getSuit() == Suit.HEARTS) {
                heartCount++;
            }
            if (opnCard.get(pos).getSuit() == Suit.DIAMONDS) {
                diamondCount++;
            }
        }
        //finds largest same suit count sum.
        int[] countArray = new int[]{spadeCount, clubCount, heartCount, diamondCount};
        for (int pos = 0; pos < countArray.length; pos++) {
            if (countArray[pos] > returnCount) {
                returnCount = countArray[pos];
            }
        }
        return returnCount >= size;
    }

    //check boolean returns
    private static int highCard(ArrayList<Card> opnCard) {
        int returnAmt = 0;
        for (int pos = 0; pos < opnCard.size(); pos++) {
            if (opnCard.get(pos).getRank() == ACE) {
                returnAmt = 13;
            }
            if (opnCard.get(pos).getRank().getNumVal() > returnAmt) {
                returnAmt = opnCard.get(pos).getRank().getNumVal() - 1;
            }
        }
        return returnAmt;
    }


    private static String OAKofAKind(ArrayList<Card> opnCard) {
        //first of a kind (OAK) set to find
        ArrayList<Card> oakOne;
        //completely new array list so cards can be removed and what not without affecting the original array list

        ArrayList<Card> open = new ArrayList<>();
        open.addAll(opnCard);
        oakOne = ofAKind(open);
        if (oakOne.size() == 4) {
            return "fourOAK";
        }

        //remove intersection of oakOne and the master set of open cards
       /* for (int pos = 0; pos < opnCard.size(); pos++) {
            for (int posit = 0; pos < oakOne.size(); posit++) {
                if (oakOne.get(posit) == opnCard.get(pos)) {
                    //opnCard.remove(pos);
                    break;
                }
            }
        }*/
        open.removeAll(oakOne);
        //second of a kind set
        ArrayList<Card> oakTwo = ofAKind(open);
        //check for oak type
        if (oakOne.size() == 2 && oakTwo.size() < 2) {
            return "pair";
        }
        if (oakOne.size() == 2 && oakTwo.size() == 2) {
            return "two pairs";
        }
        if (oakOne.size() >= 3 && oakTwo.size() != 2) {
            return "threeOAK";
        }
        if (oakOne.size() + oakTwo.size() == 5) {
            return "full house";
        } else {
            return "none";
        }

        //make union of oakOne and oakTwo old code
//        ArrayList<Card> oakR = new ArrayList<>();
//
//        oakR.addAll(oakOne);
//        oakR.addAll(oakTwo);
        //use "return" version of card set (OAKr)'s size to determine what kind of "of a kind" is on the board and in the pocket of the player inquiring
        /*switch (oakR.size()) {
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
        }*/
        //plan: get two arraylists determine if its two pairs,
        // a full house or a single of a kind and send back an String value based on that
    }

    private static String straightFlush(ArrayList<Card> opnCard) {
        //check for sequence if straight return "straight"
        ArrayList<Rank> ranks = ranksList(opnCard);
        Rank[] royals = new Rank[]{TEN, JACK, QUEEN, KING, ACE};
        boolean seq = sequence(ranks);
        //check for same suit if straightFlush return "straightFlush"
        boolean flush = sameSuit(opnCard);

        //if sequence and same suit check pass, then check lowest card value post sort, if 10, return "royalFlush"
        Collections.sort(ranks);
        if (ranks.containsAll(Arrays.asList(royals)) && flush) {
            return "royalFlush";
        }
        if (seq && flush) {
            return "straightFlush";
        }
        if (flush) {
            return "flush";
        }
        if (seq) {
            return "straight";
        } else {
            return "none";
        }
    }

    //other stuff
    private static ArrayList<Rank> ranksList(ArrayList<Card> opnCard) {
        //make list of ranks from the open cards
        ArrayList<Rank> ranks = new ArrayList<>();
        for (int pos = 0; pos < opnCard.size(); pos++) {
            //add rank to list
            ranks.add(opnCard.get(pos).getRank());
        }
        return ranks;
    }


}






