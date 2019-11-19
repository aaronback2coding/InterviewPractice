package com.aaron.game;


public class Card {
    protected int val;
    protected Suit suit;

    public Card(int val, Suit suit) {
        this.val = val;
        this.suit = suit;
    }

    public int getVal() {
        return val;
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
