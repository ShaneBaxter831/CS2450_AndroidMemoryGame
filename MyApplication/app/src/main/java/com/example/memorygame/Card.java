/***********************************************************
 * file: Card.java
 * author: Shane, Marvin
 * class: CS 2450
 *
 * Assignment: Android App
 * Date last modified: 12/12/2022
 *
 * Purpose: This class sets up the "card"/tile UI
 * and functionality for the memory game.
 *
 ***********************************************************/


package com.example.memorygame;

import android.view.View;
import android.widget.ImageButton;

public class Card {

    private Card matchingCard;
    private int frontPictureId;
    private int backPictureId;
    private ImageButton faceButton;
    private boolean flipped;
    public boolean disabled;


    /** Constructor
     * purpose: sets up the details for each individual card.
     *
     * @param frontId Identifier for the front image.
     * @param backId Identifier for the back image.
     * @param face The image button for the card.
     */
    public Card(int frontId, int backId, ImageButton face){

        this.frontPictureId = frontId;
        this.backPictureId = backId;
        this.faceButton = face;
        this.flipped = false;
        this.disabled = false;
    }



    /** setMatchingCard
     * purpose: sets which card is supposed to match with
     * the current card.
     *
     * @param match Reference to the matching card.
     *
     */
    public void setMatchingCard(Card match){
        this.matchingCard = match;
    }



    /** checkMatchingCard
     * purpose: checks if a given card is the correct
     * matching card
     *
     * @param test A given card.
     * @return True if the card is matching, false if not.
     */
    public boolean checkMatchingCard(Card test){
        if(test == matchingCard){
            return true;
        }
        else{
            return false;
        }
    }



    /** setUpListener
     * purpose: Sets up the onClickListener for the button
     * for the current card.
     *
     */
    public void setUpListener(){
        if(faceButton == null)
            return;
        faceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipUp();
            }
        });
    }



    /** flipUp
     * purpose: Switches the current button image to the
     * "front" image, and sets flipped to true.
     *
     */
    public void flipUp(){
        faceButton.setImageResource(frontPictureId);
        flipped = true;
    }



    /** flipDown
     * purpose: Swithces the current button image to the
     * "back" image, and sets flipped to false.
     *
     */
    public void flipDown(){
        faceButton.setImageResource(backPictureId);
        flipped = false;
    }



    /** isFlipped
     * purpose: checks if card is flipped face up
     *
     * @return True if the card is flipped, false if not.
     */
    public boolean isFlipped(){
        return flipped;
    }



    /** disable
     * purpose: disables the card's button, making
     *  it unable to be clicked. Sets disabled to true.
     *
     */
    public void disable(){
        faceButton.setClickable(false);
        disabled = true;
    }



    /** enable
     * purpose: enables the card's button, allowing
     * for the card to be clicked/flipped/chosen.
     * Sets disabled to false.
     */
    public void enable(){
        faceButton.setClickable(true);
        disabled = false;
    }

}
