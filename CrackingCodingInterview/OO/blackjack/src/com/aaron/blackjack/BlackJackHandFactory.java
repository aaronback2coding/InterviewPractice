package com.aaron.blackjack;

import com.aaron.game.Hand;
import com.aaron.game.HandFactory;

public class BlackJackHandFactory implements HandFactory {
    @Override
    public Hand create() {
        return new BlackJackHand();
    }
}
