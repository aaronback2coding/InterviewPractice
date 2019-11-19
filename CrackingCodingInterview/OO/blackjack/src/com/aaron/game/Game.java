package com.aaron.game;

import java.util.ArrayList;

public class Game <T extends Hand> {
    private Deck deck;
    private ArrayList<T> hands;
    private int numberofHands = 0;

    public Game(int numberofHands, HandFactory<T> handfactory) {
        this.numberofHands = numberofHands;
        if(numberofHands <= 0)
            throw new IllegalArgumentException("no players playing the game");
        this.deck = new Deck();
        this.hands = new ArrayList<>();
        for(int i = 0; i < numberofHands; i++) {
            hands.add(handfactory.create());
        }
    }

    public void printHands() {
        for(Hand hand: hands) {
            hand.print();
        }
    }

}
