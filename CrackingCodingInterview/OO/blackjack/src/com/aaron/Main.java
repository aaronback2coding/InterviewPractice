package com.aaron;

import com.aaron.blackjack.BlackJackGame;
import com.aaron.blackjack.BlackJackHandFactory;

public class Main {

    public static void main(String[] args) {
        BlackJackHandFactory handfactory = new BlackJackHandFactory();
        BlackJackGame game = new BlackJackGame(5, handfactory);
        game.play();


    }
}
