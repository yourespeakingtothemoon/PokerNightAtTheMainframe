package PokerNight.View;

import PokerNight.DAL.DAPoker;
import org.json.simple.parser.ParseException;

import java.io.IOException;
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

                    switch (new Random().nextInt(2) + 1) {
                        case 1:
                            dialogue = "\"" + quoteToAppend + "\"," + name + " said with malice in its canned voice";
                            break;
                        case 2:
                            dialogue = "shaking the table with anything it has to do so, " + name + " laughed out,\n" + "\"" + quoteToAppend.toUpperCase() + "\"";
                            break;
                        case 3:
                            dialogue = "\"" + quoteToAppend + "\"," + name + " blurted out";
                            break;
                    }

                }
        }
        return dialogue;
    }








}
