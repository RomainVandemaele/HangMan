package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hangman.databinding.ActivityMainBinding;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HangMan hanger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        this.hanger =  new HangMan(8);
        this.updateDisplay();
        this.binding.btnMainSendLetter.setOnClickListener(this::sendLetter);
        this.binding.btnMainNewGame.setOnClickListener(this::newGame);


    }

    private void newGame(View view) {
        this.hanger.initGame();
        this.updateDisplay();
    }

    private void sendLetter(View view) {
        if(this.hanger.getRemainingTries() > 0 && !this.hanger.isOver()) {
            String letter = this.binding.etMainLetter.getText().toString();
            if(letter!= null && letter.length()==1) {
                this.hanger.enterLetter(letter.charAt(0));
                this.updateDisplay();

                if(this.hanger.isOver()) {
                    Toast.makeText(this,"CONGRATULATIONS!!!!!!!!",Toast.LENGTH_LONG).show();
                }else if(this.hanger.getRemainingTries()==0){
                    this.binding.tvMainDisplay.setText(this.hanger.getWordToGuess());
                    Toast.makeText(this,"You failed at finding the word.",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,"You must fill the letter with one and only one letter",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void updateDisplay() {
        binding.tvMainDisplay.setText(this.hanger.getHiddenWord());
        binding.tvMainTrie.setText("Number of tries : " + this.hanger.getRemainingTries());
        binding.etMainLetter.setText("");
    }
}