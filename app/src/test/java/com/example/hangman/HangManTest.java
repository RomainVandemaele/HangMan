package com.example.hangman;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HangManTest {

    private HangMan hangMan;

    @Before
    public void setUp() throws Exception {
        this.hangMan = new HangMan(8);
        this.hangMan.initGame();
    }

    @Test
    public void initGameGuessWord() {
        assertNotNull(this.hangMan.getWordToGuess());
    }

    @Test
    public void initGameHiddenWord() {
        assertNotNull(this.hangMan.getHiddenWord());
    }

    @Test
    public void initGameHiddenWordSameLengthGuessWord() {
        assertEquals(this.hangMan.getHiddenWord().length(),this.hangMan.getWordToGuess().length());
    }

    @Test
    public void initGameHiddenWordSameLengthStarGuessWord() {
        String guessWord = this.hangMan.getWordToGuess();
        String expected = "";
        for (int i =0;i<guessWord.length();++i ) {
            expected += "*";
        }
        assertEquals(expected, this.hangMan.getHiddenWord());
    }

    @Test
    public void initGameNumberTries() {
        assertEquals(8,this.hangMan.getRemainingTries());
    }

    @Test
    public void enterLetterCorrectLetterTries() {
        this.hangMan.setWordToGuess("hello");
        this.hangMan.enterLetter('h');
        assertEquals(8,this.hangMan.getRemainingTries());
    }

    @Test
    public void enterLetterWrongLetterTries() {
        this.hangMan.setWordToGuess("hello");
        this.hangMan.enterLetter('h');
        assertEquals(7,this.hangMan.getRemainingTries());
    }

    @Test
    public void enterLetterCorrectLetterHiddenWord() {
        this.hangMan.setWordToGuess("hello");
        this.hangMan.enterLetter('h');
        assertEquals('h',this.hangMan.getHiddenWord().charAt(0));
    }

    @Test
    public void enterLetterWrongLetterHiddenWord() {
        this.hangMan.setWordToGuess("hello");
        this.hangMan.enterLetter('h');
        assertEquals('*',this.hangMan.getHiddenWord().charAt(0));
    }

    @Test
    public void enterLetterGuessWord() {
        this.hangMan.setWordToGuess("hello");
        this.hangMan.enterLetter('h');
        assertEquals("hello",this.hangMan.getWordToGuess());
    }


    @Test
    public void isOver() {
        this.hangMan.setWordToGuess("hello");
        this.hangMan.setHiddenWord("hello");
        assertTrue(this.hangMan.isOver());
    }

    @Test
    public void isOverFalse() {
        this.hangMan.setWordToGuess("hello");
        this.hangMan.setHiddenWord("h*llo");
        assertFalse(this.hangMan.isOver());
    }



}