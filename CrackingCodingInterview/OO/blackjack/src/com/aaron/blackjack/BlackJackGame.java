package com.aaron.blackjack;

import com.aaron.game.Card;
import com.aaron.game.Game;
import com.aaron.game.Hand;
import com.aaron.game.HandFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BlackJackGame extends Game {

    public BlackJackGame(int numberofHands, HandFactory blackjackHandFactory) {
        super(numberofHands, blackjackHandFactory);
    }

    public boolean play() {

        if(super.deck == null)
            return false;
        if(super.hands == null)
            return false;

        super.deck.shuffle();

        for(Object obj: hands) {
            BlackJackHand hand = (BlackJackHand) obj;
            while(hand.more()) {
                Card card = deck.dealCards();
                hand.addCard(card);
            }
        }
        Collections.sort(hands,  Collections.reverseOrder());
        for(Object obj: hands) {
            BlackJackHand hand = (BlackJackHand) obj;
            hand.print();
        }
        return true;
    }
}
