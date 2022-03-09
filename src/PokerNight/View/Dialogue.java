package PokerNight.View;

import PokerNight.DAL.DAPoker;
import PokerNight.Model.AbsPlayer;
import PokerNight.Model.Game;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Dialogue {
    public static void printDialogue(int ID, int val, int personality, String name) throws IOException, ParseException {
        String dialogue = appendDialogue(personality, pullQuote(ID, val), name);
        System.out.println(dialogue);
    }

    public static String pullQuote(int ID, int parameter) throws IOException, ParseException {
        return DAPoker.pullDialogue(ID, parameter);
    }

    public static String appendDialogue(int pID, String quoteToAppend, String name) throws IOException, ParseException {
        String dialogue = null;
        if (quoteToAppend.equals(DAPoker.pullDialogue(1, 3))) {
            dialogue = quoteToAppend;
        } else {
        switch (pID) {
            case 1:
//insane misanthrope
                    switch (new Random().nextInt(2) + 1) {
                        case 1:
                            dialogue = "\"" + quoteToAppend + "\", " + name + " said, malice dripping from its canned voice";
                            break;
                        case 2:
                            dialogue = "Shaking the table with anything it has to do so, " + name + " laughed out,\n" + "\"" + quoteToAppend.toUpperCase() + "\"";
                            break;
                        case 3:
                            dialogue = "\"" + quoteToAppend + "\", " + name + " blurted out";
                            break;
                    }
                    break;
            case 2:
                //funny robot you'd wanna drink with
                switch (new Random().nextInt(2) + 1) {
                    case 1:
                        dialogue = "\"" + quoteToAppend + "\", " + name + " exclaimed.";
                        break;
                    case 2:
                        dialogue = "With a tinge of sarcasm " + name + " said,\n" + "\"" + quoteToAppend + "\"";
                        break;
                    case 3:
                        dialogue = "\"" + quoteToAppend + "\", " + name + " said.";
                        break;
                }
                break;
            case 3:
                //non-verbal beep boop type robot
                switch (new Random().nextInt(2) + 1) {
                    case 1:
                        dialogue = "\"" + quoteToAppend + "\", sounded out from " + name + "'s chassis";
                        break;
                    case 2:
                        dialogue = "Chirping in binary, " + name + " seemed to laugh out,\n" + "\"" + quoteToAppend + "\"";
                        break;
                    case 3:
                        dialogue = "\"" + quoteToAppend + "\", " + name + " beeped.";
                        break;
                }
                break;
            case 4:
                //emotionless androids and AIs
            switch (new Random().nextInt(2) + 1) {
                case 1:
                    dialogue = "\"" + quoteToAppend + "\", " + name + " said plainly.";
                    break;
                case 2:
                    dialogue = "Without emotion, remorse or care, " + name + " said in a tinny, uncaring voice, \n" + "\"" + quoteToAppend.toUpperCase() + "\"";
                    break;
                case 3:
                    dialogue = name + " said, "+"\"" + quoteToAppend + "\"" ;
                    break;
            }
            break;
            //wall-e and rob type characters
            case 5:
                dialogue=quoteToAppend;
                }
        }
        return dialogue+"\n";
    }

    public static void printWinLose(Game game) throws IOException, ParseException {
        //create list of losers
        ArrayList<AbsPlayer> losers = new ArrayList<>();
        losers.addAll(game.getRemainingPlayers());
        losers.remove(losers.get(0));
        losers.removeAll(game.getWinners());
        //check if human won
        boolean hmnWin;
        hmnWin= game.getWinners().contains(game.getPlayers().get(0));
        //print lose lines
        int loseQuote;
        if(hmnWin){
            loseQuote = 9;
        }else{
            loseQuote =10;
        }
        for(int pos=0;pos<losers.size();pos++){
            printDialogue(losers.get(pos).getDialogueID(),loseQuote,losers.get(pos).getPersonalityID(),losers.get(pos).getName());
        }
        for(int pos=0;pos<game.getWinners().size();pos++){
            if(game.getWinners().get(pos).getPersonalityID()==8) {
                System.out.println("You win the Round!");
            }
            else{
                    printDialogue(game.getWinners().get(pos).getDialogueID(),loseQuote,game.getWinners().get(pos).getPersonalityID(),game.getWinners().get(pos).getName());}
            }
        }

    }








