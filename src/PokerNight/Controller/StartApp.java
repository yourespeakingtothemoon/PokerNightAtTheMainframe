package PokerNight.Controller;

import PokerNight.View.UI;
import lib.ConsoleIO;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class StartApp {
//    public void Launch() {
//        //May use to initialize some variables before calling the menu
//        MainMenu();
//    }

    public void MainMenu() throws InterruptedException, IOException, ParseException {
        GamePlay gamePlay = new GamePlay();
        UI ui = new UI();
        ui.titleScreen();
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
