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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_high_score);

        newScore = getIntent().getIntExtra("SCORE", 0);
        numCards = getIntent().getIntExtra("NUM_CARDS", 8);

        TextView displayScore = findViewById(R.id.newScore);
        displayScore.setText("Your Score: " + newScore);

        EditText nameText = findViewById(R.id.editTextTextPersonName);

        //When done button is pressed the new highscore and inputted name for the high score
        //are saved to Shared Preferences
        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameText.getText().toString();

                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("highscore" + numCards, newScore);
                editor.putString("highscore" + numCards + "name", name);
                editor.apply();

                Intent mainMenuIntent = new Intent(NewHighScoreActivity.this, MainActivity.class);
                startActivity(mainMenuIntent);

            }
        });

    }
}
