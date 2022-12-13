/***********************************************************
 * file: CardManager.java
 * author:
 * class: CS 2450
 *
 * Assignment: Android App
 * Date last modified: 12/12/2022
 *
 * Purpose: This class manages the "cards"/tiles that are used
 * in the memory game and checks the status of the cards that
 * are flipped or unflipped, and disabled (correctly chosen).
 *
 ***********************************************************/



package com.example.memorygame;

public class CardManager {
    private final Card[] cards;


    public CardManager(Card[] in) {
        this.cards = in;
    }


    /** flipUpAll
     * purpose: flips up all the cards
     *
     */
    public void flipUpAll() {
        for (int i = 0; i < cards.length; i++) {
            cards[i].flipUp();
        }
    }



    /** numberFlipped
     * purpose: returns number of cards that are flipped up and not disabled
     *
     * @return number of cards that are fliped up and not disabled.
     */
    public int numberFlipped() {
        int c = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].isFlipped() && !cards[i].disabled) {
                c++;
            }
        }
        return c;
    }


    /** cardsFlipped
     * purpose: returns all cards that are flipped up and not disabled yet
     *
     * @return all cards that are flipped up and not disabled yet
     */
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


    /** toString
     *
     * @return A string representation of the status of each
     * card being flipped or unflipped.
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < cards.length; i++) {
            s += cards[i].isFlipped() + " ";
        }
        return s;
    }


    /** numberDisabled
     *
     * @return the number of cards that are disabled.
     */
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
