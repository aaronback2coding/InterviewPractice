package com.aaron.game;

import java.util.ArrayList;

public class Hand {
    protected ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<Card>();
    }

    public void print() {
        for(Card card: cards) {
            System.out.println(card.getStr());
        }
    }
    public void addCard(Card card) {
        cards.add(card);
    }

}
