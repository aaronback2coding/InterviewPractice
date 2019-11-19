package com.aaron.game;

import java.util.ArrayList;
import java.util.Random;

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
        Random rand = new Random();

        for (int i = 0; i < cards.size(); i++) {
            int j = rand.nextInt(cards.size() - 1);
            Card card1 = cards.get(i);
            Card card2 = cards.get(j);
            cards.set(i, card2);
            cards.set(j, card1);
        }
        return;
    }

    public Card dealCards() {
        int size = cards.size();
        if(size == 0)
            return null;
        return cards.remove(size - 1);
    }
}

