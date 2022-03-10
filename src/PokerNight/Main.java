package PokerNight;

import PokerNight.Controller.StartApp;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        StartApp startApp = new StartApp();
        startApp.MainMenu();
    }
}


//code for testing
/* ArrayList<Card> testFlush = new ArrayList<>();
        Deck deck = new Deck();
        ArrayList<Card> deck2 = new ArrayList<>();
        deck.GenerateDeck(deck2);
        for(int pos=0;pos<=5;pos++){
            testFlush.add(deck2.get(pos));
        }
        System.out.println(testFlush);
        ArrayList<Card> testPocket=new ArrayList<>();
        testPocket.add(testFlush.get(0));
        testPocket.add(testFlush.get(1));
        testFlush.removeAll(testPocket);
        System.out.println(testFlush);
        System.out.println(testPocket);
        System.out.println(Checks.probScore(5,testPocket,testFlush));
        testFlush.remove(testFlush.get(1));
        testFlush.add(testPocket.get(1));
        System.out.println(Checks.probScore(5,testPocket,testFlush));*/
 /* Dialogue.printDialogue(1,9,1,"AM");
        Dialogue.printDialogue(1,3,1,"AM");*/
//Dialogue.printDialogue(3,6,4,"NORM");