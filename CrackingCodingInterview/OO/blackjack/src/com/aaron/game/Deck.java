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

    public Deck() {
        this.cards = new ArrayList<Card>();
        initSuit(Suit.CLUB);
        initSuit(Suit.DIAMONDS);
        initSuit(Suit.HEARTS);
        initSuit(Suit.SPADERS);
    }

    public void shuffle() {
        return;
    }

    public Card dealCards() {
        int size = cards.size();
        if(size == 0)
            return null;
        return cards.remove(size - 1);
    }
}

