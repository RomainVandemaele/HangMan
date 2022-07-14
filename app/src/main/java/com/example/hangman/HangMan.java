package com.example.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangMan {
    private int remainingTries;
    private String wordToGuess;
    private StringBuilder hiddenWord;
    private final int MAX_TRIES;

    public HangMan(int numberOfTries) {
        MAX_TRIES = numberOfTries;
        this.initGame();
    }

    public void initGame() {
        this.remainingTries = MAX_TRIES;
        List<String> words = new ArrayList<>();
        words.add("abilities");
        words.add("abroad");
        words.add("academic");
        words.add("pension");
        words.add("performed");
        words.add("strawberry");
        words.add("sophisticated");
        words.add("electrical");
        words.add("kotlin");
        words.add("java");
        Random sr = new Random();
        wordToGuess = words.get(sr.nextInt(words.size()) );
        hiddenWord = new StringBuilder();
        for (int i=0;i<wordToGuess.length();++i) {
            hiddenWord.append("*");
        }
    }

    public boolean enterLetter(char letter) {
        boolean letterFound = false;
        for(int i=0;i<this.wordToGuess.length();i++) {
            if(this.wordToGuess.charAt(i)==letter) {
                hiddenWord.replace(i,i+1,String.valueOf(letter));
                letterFound = true;
            }
        }
        if(!letterFound) {
            remainingTries--;
        }
        return letterFound;
    }

    private void setRemainingTries(int remainingTries) {
        this.remainingTries = remainingTries;
    }

    private void setHiddenWord(StringBuilder hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public boolean isOver() {
        return this.hiddenWord.toString().equals(this.wordToGuess);
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public int getRemainingTries() {
        return remainingTries;
    }

    public String getHiddenWord() {
        return hiddenWord.toString();
    }
}
