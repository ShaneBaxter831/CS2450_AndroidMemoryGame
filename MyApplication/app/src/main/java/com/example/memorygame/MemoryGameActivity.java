/***********************************************************
 * file: MemoryGameActivity.java
 * author: Ethan, Shane
 * class: CS 2450
 *
 * Assignment: Android App
 * Date last modified: 12/12/2022
 *
 * Purpose: This activity sets up the functionality and UI
 * for the memory game. It utilizes the specilty classes
 * for the cards and the game selection from the MainActivity
 * class to display the correct game, as well as initiate the prompt
 * to store the final score if it greater than the high score
 * set in the preferences.
 *
 ***********************************************************/



package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

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
    private Button tryagainButton;
    private Player currentPlayer;
    private TextView score;


    /** onCreate
     * Purpose: Overridden onCreate method used to set up the UI
     * of the correct memory game as passed in from the MainActivity.
     *
     * @param savedInstanceState The bundle of the instance state at
     *                           creation.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);

        numCards = getIntent().getIntExtra("NUM_CARDS", 0);

        if (numCards == 4)
            setContentView(R.layout.four_card_game);
        if (numCards == 6)
            setContentView(R.layout.six_card_game);
        if (numCards == 8)
            setContentView(R.layout.eight_card_game);
        if (numCards == 10)
            setContentView(R.layout.ten_card_game);
        if (numCards == 12)
            setContentView(R.layout.twelve_card_game);
        if (numCards == 14)
            setContentView(R.layout.fourteen_card_game);
        if (numCards == 16)
            setContentView(R.layout.sixteen_card_game);
        if (numCards == 18)
            setContentView(R.layout.eighteen_card_game);
        if (numCards == 20)
            setContentView(R.layout.twenty_card_game);


        backButton = (Button) findViewById(R.id.backButton);
        backButton.setText("End Game");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Stuff that updates the UI
                        currentPlayer.resetScore();
                    }
                });
                cardHolder.flipUpAll(); // show user corrects answers
                delayEndGame();
            }
        });

        tryagainButton = (Button)findViewById(R.id.tryAgainButton);
        tryagainButton.setText("Try Again");
        tryagainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MemoryGameActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", numCards);
                startActivity(gameIntent);
            }
        });


        cardsBeingUsed = new Card[numCards];
        labelList = new Integer[numCards];

        for (int i = 0; i < numCards; i++) {
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

        backFace = R.drawable.classycard;

        allCardButtons = new ImageButton[]{findViewById(R.id.card1), findViewById(R.id.card2), findViewById(R.id.card3), findViewById(R.id.card4),
                findViewById(R.id.card5), findViewById(R.id.card6), findViewById(R.id.card7), findViewById(R.id.card8), findViewById(R.id.card9),
                findViewById(R.id.card10), findViewById(R.id.card11), findViewById(R.id.card12), findViewById(R.id.card13), findViewById(R.id.card14),
                findViewById(R.id.card15), findViewById(R.id.card16), findViewById(R.id.card17), findViewById(R.id.card18), findViewById(R.id.card19),
                findViewById(R.id.card20)};
        int j = 0;
        for (int i = 0; i < numCards; i += 2) {
            cardsBeingUsed[i] = new Card(allPossiblePictures[j], backFace, allCardButtons[labelList[i]]);
            cardsBeingUsed[i + 1] = new Card(allPossiblePictures[j], backFace, allCardButtons[labelList[i + 1]]);
            cardsBeingUsed[i].setMatchingCard(cardsBeingUsed[i + 1]);
            cardsBeingUsed[i + 1].setMatchingCard(cardsBeingUsed[i + 1]);
            cardsBeingUsed[i].setUpListener();
            cardsBeingUsed[i + 1].setUpListener();
            j++;
        }

        score = (TextView) findViewById(R.id.scoreText);
        currentPlayer = new Player(score);
        cardHolder = new CardManager(cardsBeingUsed);

        runGame(cardHolder);
    }



    /** runGame
     * Puspose: Sets up a timer based game sequence that
     * automatically checks if flipped pairs are matched,
     * and if not, reflips them. Also checks if, after
     * the game finished, the score is the new
     * high score.
     *
     * @param ch The CardManager of the correct type of
     *           memory game being played, storing all the
     *           cards being used.
     */
    private void runGame(CardManager ch) {
        task = new TimerTask() {
            int s1 = 0;
            int s2 = 0;

            @Override
            public void run() {
                if (ch.numberFlipped() > 1 && s1 < 1) {
                    s1++;
                    int[] temp = ch.cardsFlipped();
                    //right answer
                    if (cardsBeingUsed[temp[0]].checkMatchingCard(cardsBeingUsed[temp[1]])) {
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
                    else {
                        disableNotFlipped();
                        int indexOne = temp[0];
                        int indexTwo = temp[1];
                        LockSupport.parkNanos(800000000);
                        enableNotFlipped();
                        LockSupport.parkNanos(200000000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Stuff that updates the UI
                                currentPlayer.wrongAnswer();
                                cardsBeingUsed[indexOne].flipDown();
                                cardsBeingUsed[indexTwo].flipDown();
                            }
                        });
                    }
                    temp = null;
                    s1--;
                }
                //Player has correctly guessed all matches, game is over
                if (ch.numberDisabled() == numCards && s2 < 1) {
                    s2++;
                    LockSupport.parkNanos(1_000_000_000);
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    int oldHighScore = preferences.getInt("high score" + numCards, 0);
                    if (oldHighScore < currentPlayer.getScore()) {
                        Intent enterHighScoreIntent = new Intent(MemoryGameActivity.this, NewHighScoreActivity.class);
                        enterHighScoreIntent.putExtra("NUM_CARDS", numCards);
                        enterHighScoreIntent.putExtra("SCORE", currentPlayer.getScore());
                        startActivity(enterHighScoreIntent);
                    } else {
                        endGame();
                    }
                    timer.cancel();
                    s2--;
                }
            }
        };

        timer = new Timer();
        timer.schedule(task, 100, 100);
    }



    /** disableNotFlipped
     * Purpose: Disables the cards' listener and ability
     * to be flipped if they are not flipped yet.
     *
     */
    private void disableNotFlipped(){
        for(int i = 0; i < cardsBeingUsed.length; ++i){
            if(!cardsBeingUsed[i].isFlipped()){
                cardsBeingUsed[i].disable();
            }
        }
    }



    /** enableNotFlipped
     * Purpose: Enables the cards that are not flipped
     * yet to be selected/flipped.
     *
     */
    private void enableNotFlipped(){
        for(int i = 0; i < cardsBeingUsed.length; ++i){
            if(!cardsBeingUsed[i].isFlipped()){
                cardsBeingUsed[i].enable();
            }
        }
    }



    /** onStop
     * Purpose: Overridden method used to stop the timer
     * used in the memory game, then calls original onStop.
     *
     */
    @Override
    protected void onStop() {
        timer.cancel();
        timer.purge();
        super.onStop();
    }



    /** delayEndGame
     * purpose: delays returning to Main Menu so we can see answers
     *
     */
    public void delayEndGame() {
        TimerTask task = new TimerTask() {
            public void run() {
                Intent gameIntent = new Intent(MemoryGameActivity.this, MainActivity.class);
                startActivity(gameIntent);
            }
        };
        Timer timer = new Timer();
        long delay = 2000;
        timer.schedule(task, delay);
    }



    /** endGame
     * Purpose: ends the game, going back to the MainActivity.
     *
     */    public void endGame() {
        Intent gameIntent = new Intent(MemoryGameActivity.this, MainActivity.class);
        startActivity(gameIntent);
    }



    /** onOptionsItemSelected
     * purpose: Sets up ancestral navigation using the up button
     *
     * @return True, for the ancestral navigation
     */
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}

