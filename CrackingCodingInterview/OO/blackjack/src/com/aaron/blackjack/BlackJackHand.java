package com.aaron.blackjack;

import com.aaron.game.Hand;

public class BlackJackHand extends Hand {
    @Override
    public void print() {
        System.out.println("blackjack hand");
        super.print();
    }

    public int score() {
        return 0;
    }

    public boolean play() {
        return true;
    }
}
