package com.example.memorygame;

import android.view.View;
import android.widget.ImageButton;

public class Card {

    public Card matchingCard;
    public int frontPictureId;
    public ImageButton backFaceButton;

    public Card(int frontId, ImageButton back){

        this.frontPictureId = frontId;
        this.backFaceButton = back;

    }

    public void setMatchingCard(Card match){
        this.matchingCard = match;
    }

    public void setUpListener(){
        if(backFaceButton == null)
            return;
        backFaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backFaceButton.setImageResource(frontPictureId);
            }
        });

    }
}
