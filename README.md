                 ____        __                         __  __              __      __             
                /\  _`\     /\ \                       /\ \/\ \  __        /\ \    /\ \__          
                \ \ \L\ \___\ \ \/'\      __   _ __    \ \ `\\ \/\_\     __\ \ \___\ \ ,_\         
                 \ \ ,__/ __`\ \ , <    /'__`\/\`'__\   \ \ , ` \/\ \  /'_ `\ \  _ `\ \ \/         
                  \ \ \/\ \L\ \ \ \\`\ /\  __/\ \ \/     \ \ \`\ \ \ \/\ \L\ \ \ \ \ \ \ \_        
                   \ \_\ \____/\ \_\ \_\ \____\\ \_\      \ \_\ \_\ \_\ \____ \ \_\ \_\ \__\       
                    \/_/\/___/  \/_/\/_/\/____/ \/_/       \/_/\/_/\/_/\/___L\ \/_/\/_/\/__/       
                                                                         /\____/                   
                                                                         \_/__/                    

              __        __    __                                                    ___                                     
              /\ \__    /\ \__/\ \                  /'\_/`\            __          /'___\                                    
         __  \ \ ,_\   \ \ ,_\ \ \___      __     /\      \     __   /\_\    ___ /\ \__/  _ __    __      ___ ___      __   
       /'__`\ \ \ \/    \ \ \/\ \  _ `\  /'__`\   \ \ \__\ \  /'__`\ \/\ \ /' _ `\ \ ,__\/\`'__\/'__`\  /' __` __`\  /'__`\ 
      /\ \L\.\_\ \ \_    \ \ \_\ \ \ \ \/\  __/    \ \ \_/\ \/\ \L\.\_\ \ \/\ \/\ \ \ \_/\ \ \//\ \L\.\_/\ \/\ \/\ \/\  __/ 
      \ \__/.\_\\ \__\    \ \__\\ \_\ \_\ \____\    \ \_\\ \_\ \__/.\_\\ \_\ \_\ \_\ \_\  \ \_\\ \__/.\_\ \_\ \_\ \_\ \____\
       \/__/\/_/ \/__/     \/__/ \/_/\/_/\/____/     \/_/ \/_/\/__/\/_/ \/_/\/_/\/_/\/_/   \/_/ \/__/\/_/\/_/\/_/\/_/\/____/


# "Poker Night" OOP Final - Christian Dahl & Brandon Smith

### Description

Poker Night at the Mainframe is a console-based Texas Hold 'Em Poker simulator with AI-controlled opponents based on various popular robots from media.
The initial inspiration for this project was the Telltale Games game, Poker Night at the Inventory. We wanted to create a sort of culmination of games and projects created in OOP but also put our own spin on it.

### Features

* Robust Poker Mechanics
  * Player interaction via command line
  * Fully modeled and randomized card deck
  * Flop, Turn, River
  * Hand value calculation and comparison
  * Calculated and tracked pot and bank values
* AI-Controlled Opponents
  * Based on memorable characters from various pieces of media
    * C-3PO, GLaDOS, Claptrap, Baymax, and many more
  * 4 archetypes
    * Alpha, Beta, Sigma, Omega
    * Each has a different playstyle
  * Over 100 lines of dialogue!
    * Written specific to each robot
    * Flavorful emotion text around quotes
  
# Installation
### Direct Install:

1. Go to [bit.ly/PNInstall](http://bit.ly/PNInstall)
2. Save the .exe to a folder of your choice
3. Follow the installation wizard
4. Go to the location you downloaded the Poker Night application or search it and run the program to play (will run in a commandline window)

### "Portable" Version:

1. Go to [bit.ly/PNPortable](http://bit.ly/PNPortable) or download the .zip from this page
2. Unzip the `PokerNight` folder to a folder of your choice
3. Run the .exe to open the program (will run in a commandline window)

# How to Play
### Texas Hold 'Em Rules + Controls
* use a, b, c, or d to select options in the menu
* At the beginnning of the round everyone will have to pay in or "ante up"
* `Call` bets the amount the last player put in
* `Fold` takes you out of the round without having to bet
* `Bet` allows you to choose an amount to bet above whats been bet before and raise the minimum bet
* `Check` is only available after the first betting round, and allows you to stay in the game without betting for the round
    
    
The goal is to bet against other players with the knowledge of your hand    
Your hand is the highest value 5-card hand chosen from your 2 pocket cards and 5 cards on the table    
Possible hands can be found [here](https://en.wikipedia.org/wiki/Texas_hold_%27em#Hand_values)    

Winner(s) take the pot, or the total of money thrown in throughout the game.
The game will repeat until one player takes the entire amount of money on the table at the beginning (50,000)

### CPU Player Archetypes

* Alpha - Aggressive playstyle
* Beta - Careful playstyle
* Sigma - Random playstyle
* Omega - Smart playstyle

# Credits
Poker game and rules by [Brandon Smith (Eukaryotic)](https://github.com/EukaryoticCS)    
AI implementation and dialogue by [Christian Dahl (Mr.Moon)](https://github.com/yourespeakingtothemoon)

# DISCLAIMER
This is a STUDENT AND FAN PROJECT    
All copyrighted characters belong to their holders    
This program has no official ties to Valve, Harlan Ellison, Paramount Global/CBS, The Walt Disney Company/Lucasfilm,
2K Games, Douglas Adams, or Nintendo.    
Again this is simply a fan project, and should not be sold or purchased.
