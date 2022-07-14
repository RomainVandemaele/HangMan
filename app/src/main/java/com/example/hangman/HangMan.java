package com.example.hangman;

import android.util.Log;

import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangMan {
    private int remainingTries;
    private String wordToGuess;
    private StringBuilder hiddenWord;
    private final int MAX_TRIES;
    private List<String> words= new ArrayList<>();;

    public HangMan() {
        this(8);
    }

    public HangMan(int numberOfTries) {
        MAX_TRIES = numberOfTries;
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
    }

    public void initGame() {
        this.remainingTries = MAX_TRIES;
        Random sr = new Random();
        wordToGuess =this.selectWord();
        hiddenWord = new StringBuilder();
        for (int i=0;i<wordToGuess.length();++i) {
            hiddenWord.append("*");
        }
    }

    private String selectWord() {
        Random sr = new SecureRandom();
        try  {
            int lineIndex = sr.nextInt(10000);
            Log.d("INDEX","Index : "+lineIndex);
            FileReader fileReader = new FileReader("C:/Users/STG0805/AndroidStudioProjects/HangMan/app/src/main/worldist english.txt");
            char[] line = new char[32];
            for (int i = 0; i<lineIndex;++i) {
                fileReader.read(line);
            }
            String word = String.valueOf(line);
            fileReader.close();
            return word;
        }catch (IOException e) {
            Log.d("INDEX","Index : "+e.toString());
            return words.get(sr.nextInt(this.words.size()) );
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

    protected void setRemainingTries(int remainingTries) {
        this.remainingTries = remainingTries;
    }

    protected void setHiddenWord(String hiddenWord) {
        this.hiddenWord = new StringBuilder(hiddenWord);
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public boolean isOver() {
        return this.hiddenWord.toString().equals(this.wordToGuess);
    }

    public boolean noTryLeft() {
        return this.remainingTries<=0;
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
