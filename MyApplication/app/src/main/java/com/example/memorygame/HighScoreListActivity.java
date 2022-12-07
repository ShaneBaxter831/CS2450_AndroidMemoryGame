package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HighScoreListActivity extends AppCompatActivity {

    TextView[] allHighScores;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.high_scores);

        Button back = findViewById(R.id.backToMenu);

        //back to main menu
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(HighScoreListActivity.this, MainActivity.class);
                startActivity(main);
            }
        });

        //resets all currently saved highscores
        Button reset = findViewById(R.id.resetButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.apply();
                HighScoreListActivity.this.recreate();
            }
        });

        allHighScores = new TextView[]{findViewById(R.id.highScoreFour), findViewById(R.id.highScoreSix), findViewById(R.id.highScoreEight),
                                        findViewById(R.id.highScoreTen), findViewById(R.id.highScoreTwelve), findViewById(R.id.highScoreFourteen),
                                        findViewById(R.id.highScoreSixteen), findViewById(R.id.highScoreEighteen), findViewById(R.id.highScoreTwenty)};

        //Display all saved highscores
        for(int i=0; i<9; i++){
            int currNumberCards = 4 + (i*2);

            SharedPreferences prefs = getSharedPreferences("PREFS", 0);
            int currentHighScore = prefs.getInt("high score" + currNumberCards, 0);
            String currentHighScoreName = prefs.getString("high score" + currNumberCards + "name", "ABC");

            allHighScores[i].setText(currNumberCards + " card high score: " + currentHighScoreName + " - " + currentHighScore);
        }

    }

}
