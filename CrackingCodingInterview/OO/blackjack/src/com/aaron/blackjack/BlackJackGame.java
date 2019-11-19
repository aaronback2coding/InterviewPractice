package com.aaron.blackjack;

import com.aaron.game.Game;
import com.aaron.game.HandFactory;

public class BlackJackGame extends Game {

    public BlackJackGame(int numberofHands, HandFactory blackjackHandFactory) {
        super(numberofHands, blackjackHandFactory);
    }

    public boolean initialPlay() {
        return true;
    }

    public boolean play() {
        return true;
    }
}
