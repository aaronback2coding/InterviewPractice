package com.aaron.blackjack;

import com.aaron.game.Card;
import com.aaron.game.Suit;

public class BlackJackCard extends Card {
    public BlackJackCard(int val, Suit suit) {
        super(val, suit);
    }

    public int value() {
        return 0;
    }
}
