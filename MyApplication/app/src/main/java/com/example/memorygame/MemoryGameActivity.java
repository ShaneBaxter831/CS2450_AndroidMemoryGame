package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Random;

public class MemoryGameActivity extends AppCompatActivity {

    private ImageButton[] allCardButtons;
    private int numCards;
    private Integer[] allPossiblePictures;
    private Card[] cardsBeingUsed;

    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        numCards =  getIntent().getIntExtra("NUM_CARDS", 8);

        if(numCards == 4)
            setContentView(R.layout.four_card_game);
        if(numCards == 8)
            setContentView(R.layout.eight_card_game);
        if(numCards == 10)
            setContentView(R.layout.ten_card_game);
        if(numCards == 12)
            setContentView(R.layout.twelve_card_game);
        if(numCards == 14)
            setContentView(R.layout.fourteen_card_game);
        if(numCards == 16)
            setContentView(R.layout.sixteen_card_game);
        if(numCards == 18)
            setContentView(R.layout.eighteen_card_game);
        if(numCards == 20)
            setContentView(R.layout.twenty_card_game);


        cardsBeingUsed = new Card[numCards];


        allPossiblePictures = new Integer[40];

        //add each picture twice
        allPossiblePictures[0] = (R.drawable.ic_baseline_bug_report_24);
        allPossiblePictures[1] = (R.drawable.ic_baseline_bug_report_24);
        allPossiblePictures[2] = (R.drawable.ic_baseline_cookie_24);
        allPossiblePictures[3] = (R.drawable.ic_baseline_cookie_24);
        allPossiblePictures[4] = (R.drawable.ic_baseline_hiking_24);
        allPossiblePictures[5] = (R.drawable.ic_baseline_hiking_24);
        allPossiblePictures[6] = (R.drawable.ic_baseline_pest_control_rodent_24);
        allPossiblePictures[7] = (R.drawable.ic_baseline_pest_control_rodent_24);
        allPossiblePictures[8] = R.drawable.ic_baseline_plumbing_24;
        allPossiblePictures[9] = R.drawable.ic_baseline_plumbing_24;
        allPossiblePictures[10] = R.drawable.ic_baseline_ramen_dining_24;
        allPossiblePictures[11] = R.drawable.ic_baseline_ramen_dining_24;
        allPossiblePictures[12] = R.drawable.ic_baseline_savings_24;
        allPossiblePictures[13] = R.drawable.ic_baseline_savings_24;
        allPossiblePictures[14] = R.drawable.ic_baseline_sports_esports_24;
        allPossiblePictures[15] = R.drawable.ic_baseline_sports_esports_24;
        allPossiblePictures[16] = R.drawable.ic_baseline_rocket_launch_24;
        allPossiblePictures[17] = R.drawable.ic_baseline_rocket_launch_24;
        allPossiblePictures[18] = R.drawable.ic_baseline_toys_24;
        allPossiblePictures[19] = R.drawable.ic_baseline_toys_24;


        Random rand = new Random();




        allCardButtons = new ImageButton[]{findViewById(R.id.card1),findViewById(R.id.card2),findViewById(R.id.card3),findViewById(R.id.card4),
                                    findViewById(R.id.card5), findViewById(R.id.card6), findViewById(R.id.card7), findViewById(R.id.card8), findViewById(R.id.card9),
                                    findViewById(R.id.card10), findViewById(R.id.card11), findViewById(R.id.card12), findViewById(R.id.card13), findViewById(R.id.card14),
                                    findViewById(R.id.card15), findViewById(R.id.card16), findViewById(R.id.card17), findViewById(R.id.card18), findViewById(R.id.card19),
                                    findViewById(R.id.card20)};

        for(int i = 0; i<numCards; i++) {

            boolean foundValidIndex = false;
            int index = 0;

            while(!foundValidIndex){
                index = rand.nextInt(numCards);
                if(allPossiblePictures[index] != null)
                    foundValidIndex = true;
            }

            cardsBeingUsed[i] = new Card(allPossiblePictures[index], allCardButtons[i]);
            cardsBeingUsed[i].setUpListener();
            allPossiblePictures[index] = null;
        }
    }
}
