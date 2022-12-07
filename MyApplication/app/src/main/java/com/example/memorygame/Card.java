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

    public Card(int frontId, int backId, ImageButton face){

        this.frontPictureId = frontId;
        this.backPictureId = backId;
        this.faceButton = face;
        this.flipped = false;
        this.disabled = false;
    }

    public void setMatchingCard(Card match){
        this.matchingCard = match;
    }

    public boolean checkMatchingCard(Card test){
        if(test == matchingCard){
            return true;
        }
        else{
            return false;
        }
    }

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

    public void flipUp(){
        faceButton.setImageResource(frontPictureId);
        flipped = true;
    }

    public void flipDown(){
        faceButton.setImageResource(backPictureId);
        flipped = false;
    }

    //checks if card is flipped face up
    public boolean isFlipped(){
        return flipped;
    }

    public void disable(){
        faceButton.setClickable(false);
        disabled = true;
    }

}
