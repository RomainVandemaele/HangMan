package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.hangman.databinding.ActivityMainBinding;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HangMan hanger;
    private FrameLayout[] progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        this.hanger =  new HangMan();
        hanger.initGame();

        this.binding.btnMainSendLetter.setOnClickListener(this::sendLetter);
        this.binding.btnMainNewGame.setOnClickListener(this::newGame);

        progressBar = new FrameLayout[8];
        progressBar[0] = this.binding.flMainFl8;
        progressBar[1] = this.binding.flMainFl7;
        progressBar[2] = this.binding.flMainFl6;
        progressBar[3] = this.binding.flMainFl5;
        progressBar[4] = this.binding.flMainFl4;
        progressBar[5] = this.binding.flMainFl3;
        progressBar[6] = this.binding.flMainFl2;
        progressBar[7] = this.binding.flMainFl1;
        this.updateDisplay();
    }

    private void newGame(View view) {
        this.hanger.initGame();
        this.updateDisplay();
        for (int i =0;i<progressBar.length;++i) {
            this.progressBar[hanger.getRemainingTries()-1].setVisibility(View.INVISIBLE);
        }
    }

    private void sendLetter(View view) {
        if(this.hanger.getRemainingTries() > 0 && !this.hanger.isOver()) {
            String letter = this.binding.etMainLetter.getText().toString();
            if(letter!= null && letter.length()==1) {
                this.hanger.enterLetter(letter.charAt(0));
                this.updateDisplay();

                if(this.hanger.isOver()) {
                    Toast.makeText(this,"CONGRATULATIONS!!!!!!!!",Toast.LENGTH_LONG).show();
                }else if(this.hanger.noTryLeft()){
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
        int nTry = hanger.getRemainingTries();
        for(int i =0;i<8-nTry;i++ ) {
            this.binding.linearLayout.getChildAt(i).setAlpha(1);
        }
//        if(nTry >=0 && nTry < this.progressBar.length) {
//            this.progressBar[hanger.getRemainingTries()].setVisibility(View.VISIBLE);
//        }
        binding.tvMainTrie.setText("Number of tries : " + this.hanger.getRemainingTries());
        binding.etMainLetter.setText("");
    }
}