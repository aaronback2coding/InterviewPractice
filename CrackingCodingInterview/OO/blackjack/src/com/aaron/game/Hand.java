package com.aaron.game;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<Card>();
    }

    public void print() {
        System.out.println("Hand");
        for(Card card: cards) {
            System.out.println(card.getStr());
        }
    }
    public void addCard(Card card) {
        cards.add(card);
    }

}
