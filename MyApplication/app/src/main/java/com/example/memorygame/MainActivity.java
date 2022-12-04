package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}