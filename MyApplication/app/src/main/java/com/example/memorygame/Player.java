package com.example.memorygame;

import android.widget.TextView;

public class Player {

    private int score;
    private String name;
    public TextView scoreText;

    public Player(TextView text){
        score = 0;
        name = "";
        scoreText = text;
        scoreText.setText("Score: " + score);

    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }

    //Decrements score if player guessed wrong. Cannot put score below zero.
    public void wrongAnswer(){
        if(score > 0)
            score = score - 1;
        scoreText.setText("Score: " + score);
    }

    //Increases score on a correct guess.
    public void rightAnswer(){
        score = score +2 ;
        scoreText.setText("Score: " + score);
    }

    public void resetScore(){
        score = 0;
        scoreText.setText("Score: " + score);
    }
}
