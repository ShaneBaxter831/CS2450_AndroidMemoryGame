package com.example.memorygame;

public class CardManager {
    private final Card[] cards;


    public CardManager(Card[] in) {
        this.cards = in;
    }


    //flips up all the cards
    public void flipUpAll() {
        for (int i = 0; i < cards.length; i++) {
            cards[i].flipUp();
        }
    }

    //returns number of cards that are flipped up and not disabled
    public int numberFlipped() {
        int c = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].isFlipped() && !cards[i].disabled) {
                c++;
            }
        }
        return c;
    }

    //returns all cards that are flipped up and not disabled yet
    public int[] cardsFlipped() {
        int[] r = new int[numberFlipped()];
        int c = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].isFlipped() && !cards[i].disabled) {
                r[c] = i;
                c++;
            }
        }
        return r;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < cards.length; i++) {
            s += cards[i].isFlipped() + " ";
        }
        return s;
    }

    public int numberDisabled() {
        int c = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].disabled) {
                c++;
            }
        }
        return c;
    }

}
