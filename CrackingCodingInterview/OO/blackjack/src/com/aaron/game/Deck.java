package com.aaron.game;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;


    private void initSuit(Suit suit) {
        for(int i = 1; i <= 13; i++) {
            Card card = new Card(i, suit);
            cards.add(card);
        }

    }

    public Deck(ArrayList<Card> cards) {
        this.cards = new ArrayList<Card>();
    }
}

