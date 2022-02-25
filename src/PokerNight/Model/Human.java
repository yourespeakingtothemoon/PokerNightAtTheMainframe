package PokerNight.Model;

import lib.ConsoleIO;

import java.util.ArrayList;

public class Human extends AbsPlayer {

    @Override
    public void turn(ArrayList<Card> openCards) { //Might return the amount the player bets instead of nothing
        while (true) {
            switch(ConsoleIO.promptForString("a: Call\n" +
                    "b: Bet\n" +
                    "c: Fold\n" +
                    "d: Check\n", false)) {
                case "a": //Call
                    //Stay in the game, equal the bet of the previous player
                    return;
                case "b": //Bet
                    //Take bet amount
                        //Must be double the current bet, less than max money that the player has
                    return;
                case "c": //Fold
                    //Cards added to muck
                    //Allow player to skip to next round
                    return;
                case "d": //Check
                    //Only possible after flop... Somehow only show this option after betting round 1?
                    //Stay in game without betting
                    return;
            }
        }
    }
}
