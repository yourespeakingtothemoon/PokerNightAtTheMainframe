package PokerNight.Controller;

import lib.ConsoleIO;

public class StartApp {
//    public void Launch() {
//        //May use to initialize some variables before calling the menu
//        MainMenu();
//    }

    public void MainMenu() throws InterruptedException {
        GamePlay gamePlay = new GamePlay();
        while (true) {
            switch(ConsoleIO.promptForString("Welcome to Poker Night at the Mainframe! Please input a letter to select an option:\n" +
                    "a: Start Game\n" +
                    "b: Exit\n",
                    false)) {
                case "a": //Start Game
                    gamePlay.StartGame();
                    break;
                case "b": //Exit
                    return;
            }
        }
    }
}
