package com.aaron.game;


public class Card {
    private int val;
    private Suit suit;

    public Card(int val, Suit suit) {
        this.val = val;
        this.suit = suit;
    }

    public String getStr() {
        String str = "";
        str +=  this.val;
        switch (this.suit) {
            case CLUB :
                str += "C";
                break;
            case DIAMONDS :
                str += "D";
                break;
            case HEARTS :
                str += "H";
                break;
            case SPADERS :
                str += "S";
                break;

        }
        return str;

    }
}
