/***********************************************************
 * file: NewHighScoreActivity.java
 * author: Marvin, Shane
 * class: CS 2450
 *
 * Assignment: Android App
 * Date last modified: 12/12/2022
 *
 * Purpose: This activity is prompted when the user gets a new
 * high score in their chosen Memory Game, allowing them to store
 * their name with their high score into a file.
 *
 ***********************************************************/



package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewHighScoreActivity extends AppCompatActivity {

    private int newScore;
    private String name;
    private int numCards;



    /** onCreate
     * purpose: Overridden onCreate that sets up the activity
     * and prompt that asks for the name for the new high
     * score. Stores the high score and name into the shared preferences.
     *
     * @param savedInstanceState Bundle for the instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_high_score);

        newScore = getIntent().getIntExtra("SCORE", 0);
        numCards = getIntent().getIntExtra("NUM_CARDS", 8);

        TextView displayScore = findViewById(R.id.newScore);
        displayScore.setText("Your Score: " + newScore);

        EditText nameText = findViewById(R.id.editTextTextPersonName);

        //When done button is pressed the new high score and inputted name for the high score
        //are saved to Shared Preferences
        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameText.getText().toString();

                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("high score" + numCards, newScore);
                editor.putString("high score" + numCards + "name", name);
                editor.apply();

                Intent mainMenuIntent = new Intent(NewHighScoreActivity.this, MainActivity.class);
                startActivity(mainMenuIntent);

            }
        });

    }
}
