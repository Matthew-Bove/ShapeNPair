package com.example.shapenpairgame;


/*
 *   A player will have their name
 *   keep record of correct and incorrect moves the player will make
 *   and calculate their final score.
 */
public class Player {
    private String name;
    private int correctGuesses;
    private int incorrectGuesses;
    private int totalGuesses;
    private int score;


    public Player(String name){
        this.name = name;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public int getIncorrectGuesses() {return incorrectGuesses;}

    // incorrectGuesses and totalGuesses will be incremented

    public void IncorrectGuess() {
        incorrectGuesses++;
        totalGuesses++;
    }

    public int getCorrectGuesses() {return correctGuesses;}

    // correctGuesses will be incremented

    public void CorrectGuess() {
        correctGuesses++;
        //totalGuesses++;
    }

    public int getTotalGuesses(){return totalGuesses;}

    // calculates score by dividing number of times player has guesses correctly by number of total guesses
    // multiplying that result by 100 to get a percent value.

    public void calculateScore(){
        score = ( getCorrectGuesses() /(getCorrectGuesses()+getTotalGuesses())) * 100;
    }

    public int getScore(){return score;}

    public void resetGuesses(){
        correctGuesses = 0;
        incorrectGuesses = 0;
        totalGuesses = 0;
    }
}
