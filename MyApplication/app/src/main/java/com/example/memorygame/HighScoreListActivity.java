package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HighScoreListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.high_scores);

        Button back = findViewById(R.id.backToMenu);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(HighScoreListActivity.this, MainActivity.class);
                startActivity(main);
            }
        });

        TextView highScoreEight = findViewById(R.id.highScoreEight);
        SharedPreferences prefs = getSharedPreferences("PREFS", 0);
        int eightCardScore = prefs.getInt("highscore8", 0);
        String eightCardName = prefs.getString("highscore8name", "ABC");

        highScoreEight.setText("Eight card highscore: " + eightCardName + " - " + eightCardScore);


    }
}
