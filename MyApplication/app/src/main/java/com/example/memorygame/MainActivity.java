/***********************************************************
 * file: MainActivity.java
 * author: Ethan, Marvin, Shane, Simon
 * class: CS 2450
 *
 * Assignment: Android App
 * Date last modified: 12/12/2022
 *
 * Purpose: This Activity sets up the UI for the main menu of
 * the android application, as well as the media player and
 * buttons for the various games.
 *
 ***********************************************************/


package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    static MediaPlayer player;
    Button musicToggle;
    Boolean musicToggleBool = true;


    /** onCreate
     * purpose: overridden onCreate method that sets up the
     * main menu button options for the different memory games,
     * their listeners, and the media player for the music.
     * There is also a button set up to navigate to the
     * high scores activity.
     *
     * @param savedInstanceState A bundle for the instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicToggle = (Button)findViewById(R.id.MusicToggle);

        if(player==null)
        {
            player = MediaPlayer.create(this, R.raw.song);
            player.start();
            player.setLooping(true);
        }

        if(player.isPlaying()==true)
        {
            musicToggle.setText("Music: On");
            musicToggleBool = true;
        }else{
            musicToggle.setText("Music: Off");
            musicToggleBool = false;
        }

        Button fourButton = (Button)findViewById(R.id.fourCards);
        Button sixButton = (Button)findViewById(R.id.sixCards);
        Button eightCardButton = (Button)findViewById(R.id.eightCards);
        Button tenCardButton = (Button)findViewById(R.id.tenCards);
        Button twelveButton = (Button)findViewById(R.id.twelveCards);
        Button fourteenButton = (Button)findViewById(R.id.fourteenCards);
        Button sixteenButton = (Button)findViewById(R.id.sixteenCards);
        Button eighteenButton = (Button)findViewById(R.id.eighteenCards);
        Button twentyButton = (Button)findViewById(R.id.twentyCards);

        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", 4);
                startActivity(gameIntent);
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", 6);
                startActivity(gameIntent);
            }
        });

        eightCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent gameIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", 8);
                startActivity(gameIntent);
            }
        });

        tenCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", 10);
                startActivity(gameIntent);
            }
        });

        twelveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", 12);
                startActivity(gameIntent);
            }
        });

        fourteenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", 14);
                startActivity(gameIntent);
            }
        });

        sixteenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", 16);
                startActivity(gameIntent);
            }
        });

        eighteenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", 18);
                startActivity(gameIntent);
            }
        });

        twentyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                gameIntent.putExtra("NUM_CARDS", 20);
                startActivity(gameIntent);
            }
        });

        Button highScores = findViewById(R.id.toHighScores);
        highScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This code is for testing purposes, will be removed later
                //SharedPreferences p = getSharedPreferences("PREFS", 0);
                //SharedPreferences.Editor e = p.edit();
                //e.clear();
                //e.apply();

                Intent highScoreIntent = new Intent(MainActivity.this, HighScoreListActivity.class);
                startActivity(highScoreIntent);
            }
        });

    }


    /** onDestroy
     * purpose: overridden onDestroy method that is modified
     * to release the media player when destorying the activity.
     *
     */
    @Override
    public void onDestroy() {
        player.release();
        super.onDestroy();
    }


    /** musicToggle_function
     * purpose: toggles the music player on or off
     * and changes the button's text accordingly
     *
     * */
    public void musicToggle_function(View v)
    {
        musicToggleBool = !musicToggleBool;

        if(musicToggleBool==true)
        {
            player.start();
            musicToggle.setText("Music: On");
        }else{
            player.pause();
            musicToggle.setText("Music: Off");
        }
    }

}