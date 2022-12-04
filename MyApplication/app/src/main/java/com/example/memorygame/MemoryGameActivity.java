package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryGameActivity extends AppCompatActivity {

    private ImageButton[] allCardButtons;
    private int numCards;
    private Integer[] allPossiblePictures;
    private Card[] cardsBeingUsed;
    private Integer[] labelList;
    private Integer backFace;
    private CardManager cardHolder;
    private TimerTask task;
    private Timer timer;
    private Button backButton;
    private Player currentPlayer;
    private TextView score;

    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        numCards = getIntent().getIntExtra("NUM_CARDS", 8);

        if(numCards == 4)
            setContentView(R.layout.four_card_game);
        if(numCards == 6)
            setContentView(R.layout.six_card_game);
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


        backButton = (Button)findViewById(R.id.backButton);
        backButton.setText("End Game");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MemoryGameActivity.this, MainActivity.class);
                startActivity(gameIntent);
            }
        });



        cardsBeingUsed = new Card[numCards];
        labelList = new Integer[numCards];

        for(int i = 0; i < numCards; i++){
            labelList[i] = i;
        }
        Collections.shuffle(Arrays.asList(labelList));


        allPossiblePictures = new Integer[10];

        //add each picture twice
        allPossiblePictures[0] = (R.drawable.ic_baseline_bug_report_24);
        allPossiblePictures[1] = (R.drawable.ic_baseline_cookie_24);
        allPossiblePictures[2] = (R.drawable.ic_baseline_hiking_24);
        allPossiblePictures[3] = (R.drawable.ic_baseline_pest_control_rodent_24);
        allPossiblePictures[4] = R.drawable.ic_baseline_plumbing_24;
        allPossiblePictures[5] = R.drawable.ic_baseline_ramen_dining_24;
        allPossiblePictures[6] = R.drawable.ic_baseline_savings_24;
        allPossiblePictures[7] = R.drawable.ic_baseline_sports_esports_24;
        allPossiblePictures[8] = R.drawable.ic_baseline_rocket_launch_24;
        allPossiblePictures[9] = R.drawable.ic_baseline_toys_24;

        backFace = R.drawable.ic_baseline_tips_and_updates_24;

        allCardButtons = new ImageButton[]{findViewById(R.id.card1),findViewById(R.id.card2),findViewById(R.id.card3),findViewById(R.id.card4),
                                    findViewById(R.id.card5), findViewById(R.id.card6), findViewById(R.id.card7), findViewById(R.id.card8), findViewById(R.id.card9),
                                    findViewById(R.id.card10), findViewById(R.id.card11), findViewById(R.id.card12), findViewById(R.id.card13), findViewById(R.id.card14),
                                    findViewById(R.id.card15), findViewById(R.id.card16), findViewById(R.id.card17), findViewById(R.id.card18), findViewById(R.id.card19),
                                    findViewById(R.id.card20)};
        int j = 0;
        for(int i = 0; i<numCards; i += 2) {
            cardsBeingUsed[i] = new Card(allPossiblePictures[j], backFace, allCardButtons[labelList[i]]);
            cardsBeingUsed[i+1] = new Card(allPossiblePictures[j], backFace, allCardButtons[labelList[i+1]]);
            cardsBeingUsed[i].setMatchingCard(cardsBeingUsed[i+1]);
            cardsBeingUsed[i+1].setMatchingCard(cardsBeingUsed[i+1]);
            cardsBeingUsed[i].setUpListener();
            cardsBeingUsed[i+1].setUpListener();
            j++;
        }

        score = (TextView) findViewById(R.id.scoreText);
        currentPlayer = new Player(score);
        cardHolder = new CardManager(cardsBeingUsed);

        runGame(cardHolder);
    }

    private void runGame(CardManager ch){
        task = new TimerTask() {
            int c = 0;
            @Override
            public void run() {
                if(ch.numberFlipped() > 1){
                    int[] temp = ch.cardsFlipped();
                    //right answer
                    if(cardsBeingUsed[temp[0]].checkMatchingCard(cardsBeingUsed[temp[1]])){
                        cardsBeingUsed[temp[0]].disable();
                        cardsBeingUsed[temp[1]].disable();
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                // Stuff that updates the UI
                                currentPlayer.rightAnswer();
                            }
                        });

                    }
                    //wrong answer
                    else{
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cardsBeingUsed[temp[0]].flipDown();
                        cardsBeingUsed[temp[1]].flipDown();
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                // Stuff that updates the UI
                                currentPlayer.wrongAnswer();
                            }
                        });

                    }
                    temp = null;
                }
                //Player has correctly guessed all matches, game is over
                if(ch.numberDisabled() == numCards){
                    c++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    int oldHighScore = preferences.getInt("highscore" + numCards, 0);
                    if(oldHighScore < currentPlayer.getScore() && c < 2){
                        Intent enterHighScoreIntent = new Intent(MemoryGameActivity.this, NewHighScoreActivity.class);
                        enterHighScoreIntent.putExtra("NUM_CARDS", numCards);
                        enterHighScoreIntent.putExtra("SCORE", currentPlayer.getScore());
                        startActivity(enterHighScoreIntent);
                        System.out.print("\n\n\nTEST\n\n\n");
                    } else {
                        if(c < 2){
                            backButton.callOnClick();
                        }
                    }
                }
            }
        };
        timer = new Timer();
        timer.schedule(task,10, 10);
    }

    @Override protected void onStop(){
        super.onStop();
        timer.cancel();
        timer.purge();
    }

}
