package com.aaron.blackjack;

import com.aaron.game.Card;
import com.aaron.game.Hand;

public class BlackJackHand extends Hand implements Comparable<BlackJackHand> {
    @Override
    public void print() {
        System.out.println("-----------BlackJack Hand-----------");
        super.print();
    }

    private int getBlackJackVal(int val) {
        if (val <=0 || val > 13)
            throw new IllegalArgumentException("Card val is out of range!");
        return (val <= 10) ? val : 10;
    }

    public int score() {
        int score = 0;
        for(Card card: cards) {
            score += getBlackJackVal(card.getVal());
        }
        return score;
    }

    public boolean more() {
        int score = score();
        return score < 16 ? true : false;
    }

    @Override
    public int compareTo(BlackJackHand comparesto) {
        int score = this.score();
        score = score <= 21 ? score : 0;
        int comparetoScore = comparesto.score();
        comparetoScore = comparetoScore <= 21 ? comparetoScore : 0;
        return score - comparetoScore;
    }
}
