package PokerNight.Model;

import PokerNight.Model.Cards.Card;
import PokerNight.Model.Cards.Deck;
import PokerNight.Model.Players.AbsPlayer;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    //Initialize board ArrayList, blinds/bet amount, pot
    private int pot = 0;
    private int minBet = 0;
    private int blinds = 0;
    private int round = 0;
    private ArrayList<Card> gameDeck = new ArrayList<>();
    private ArrayList<AbsPlayer> players = new ArrayList<>();
    private ArrayList<AbsPlayer> remainingPlayers = new ArrayList<>();
    private ArrayList<AbsPlayer> winners;
    private ArrayList<Card> board = new ArrayList<>();
    private ArrayList<Card> muck = new ArrayList<>();
    private Deck deck = new Deck();

    public void NewRound() {
        this.pot = 0;
        this.blinds += 200;
        this.minBet = this.blinds;
        this.round = 0;
        this.gameDeck.clear(); //Fuck this, dude x2
        deck.GenerateDeck(gameDeck);
        Collections.shuffle(gameDeck);
        this.remainingPlayers.clear(); //Fuck this, dude
        for (int x = 0; x < this.players.size(); x++) { //Sets players for the turn
            if (!this.players.get(x).isOutOfGame()) {
                this.remainingPlayers.add(this.players.get(x));
            }
        }
        this.winners = null;
        this.board = new ArrayList<>();
        this.muck = new ArrayList<>();
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public int getMinBet() {
        return minBet;
    }

    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    public int getBlinds() {
        return blinds;
    }

    public void setBlinds(int blinds) {
        this.blinds = blinds;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public ArrayList<Card> getGameDeck() {
        return gameDeck;
    }

    public void setGameDeck(ArrayList<Card> gameDeck) {
        this.gameDeck = gameDeck;
    }

    public ArrayList<AbsPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<AbsPlayer> players) {
        this.players = players;
    }

    public ArrayList<AbsPlayer> getRemainingPlayers() {
        return remainingPlayers;
    }

    public void setRemainingPlayers(ArrayList<AbsPlayer> remainingPlayers) {
        this.remainingPlayers = remainingPlayers;
    }

    public ArrayList<AbsPlayer> getWinners() {
        return winners;
    }

    public void setWinner(ArrayList<AbsPlayer> winners) {
        this.winners = winners;
    }

    public ArrayList<Card> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Card> board) {
        this.board = board;
    }

    public ArrayList<Card> getMuck() {
        return muck;
    }

    public void setMuck(ArrayList<Card> muck) {
        this.muck = muck;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
