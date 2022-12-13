/***********************************************************
 * file: Player.java
 * author: Shane
 * class: CS 2450
 *
 * Assignment: Android App
 * Date last modified: 12/12/2022
 *
 * Purpose: This class sets up the functionality of the
 * user's score updating and the storage of their name and
 * score throughout the game.
 *
 ***********************************************************/



package com.example.memorygame;

import android.widget.TextView;

public class Player {

    private int score;
    private String name;
    public TextView scoreText;



    /** Constructor
     * purpose: sets up the Player class with the
     * default values for a new player, as well as the
     * text for the score display.
     *
     * @param text a TextView that is used to display the
     *             score of the player.
     */
    public Player(TextView text){
        score = 0;
        name = "";
        scoreText = text;
        scoreText.setText("Score: " + score);

    }


    /** getName
     *
     * @return The name of the player.
     */
    public String getName(){
        return this.name;
    }



    /** getScore
     *
     * @return An int that represents the score of the player.
     */
    public int getScore(){
        return this.score;
    }



    /** wrongAnswer
     * purpose: Decrements score if player guessed wrong.
     * Cannot put score below zero.
     *
     */
    public void wrongAnswer(){
        if(score > 0)
            score = score - 1;
        scoreText.setText("Score: " + score);
    }



    /** rightAnswer
     * purpose: Increases score on a correct guess.
     *
     */
    public void rightAnswer(){
        score = score +2 ;
        scoreText.setText("Score: " + score);
    }



    /** resetScore
     * purpose: resets the score of the player.
     *
     */
    public void resetScore(){
        score = 0;
        scoreText.setText("Score: " + score);
    }
}
