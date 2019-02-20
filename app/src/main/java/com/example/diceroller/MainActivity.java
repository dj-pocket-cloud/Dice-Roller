package com.example.diceroller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing the tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView;
import android.widget.ImageView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView sidevalue1;
    private TextView probability_value;
    private EditText guessesvalue1;
    private TextView guessresult;
    private ImageView diceimg1;
    private String guessText;
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private int sides = 6;
    private int guess = -1; //set to -1 whenever there is no value in the textfield
    private int result = 0;
    private String positiveColor = "#FF00FF00"; //for these hex values, the first two numbers indicate the transparency of the color
    private String negativeColor = "#FFFF0000"; //so FF at the beginning is fully visible, while 00 functionally hides the object with that color
    private String transparentColor = "#00000000";

    private int coinimg = R.drawable.placeholder;
    private int d4img = R.drawable.placeholder;
    private int diceimg = R.drawable.placeholder;
    private int d8img = R.drawable.placeholder;
    private int d10img = R.drawable.placeholder;
    private int d20img = R.drawable.placeholder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        probability_value = (TextView) findViewById(R.id.probability_value);
        sidevalue1 = (TextView) findViewById(R.id.sidevalue1);
        guessesvalue1 = (EditText) findViewById(R.id.guessesvalue1);
        guessresult = (TextView) findViewById(R.id.guessresult);
        diceimg1 = (ImageView) findViewById(R.id.diceimg1);

        SeekBar sidebar1 = (SeekBar) findViewById(R.id.sidebar1);
        sidebar1.setOnSeekBarChangeListener(seekBarListener);
        sidesChanged();
        clearGuessText();

        //set integer guess variable whenever the guess textfield is changed
        guessesvalue1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                guessText = guessesvalue1.getText().toString();
                if (!guessText.matches("")) {
                    guess = Integer.parseInt(guessText);
                } else {
                    guess = -1; //if textfield is left blank
                }
                clearGuessText();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {    }
            @Override
            public void afterTextChanged(Editable s) {  }
        });
        }

    private void sidesChanged(){
        double chance = (double) 1/sides;

        probability_value.setText(percentFormat.format(chance));
        sidevalue1.setText(String.valueOf(sides));
    }

    private final OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            sides = getNumSides(progress+1);
            sidesChanged();
            changePicture(sides);
            clearGuessText();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    private int getNumSides(int sliderValue){
        if(sliderValue<6)
            return sliderValue*2;
        else
            return 20;
    }

    private void changePicture(int value) {
        switch(value) {
            case 2:
                diceimg1.setImageResource(coinimg);
                break;
            case 4:
                diceimg1.setImageResource(d4img);
                break;
            case 6:
                diceimg1.setImageResource(diceimg);
                break;
            case 8:
                diceimg1.setImageResource(d8img);
                break;
            case 10:
                diceimg1.setImageResource(d10img);
                break;
            case 20:
                diceimg1.setImageResource(d20img);
                break;
        }
    }

    private void checkGuess() {
        if (guess != -1) {
            if (result == guess) {
                //update guessresult with positive message
                guessresult.setTextColor(Color.parseColor(positiveColor));
                guessresult.setText("Correct!");
            } else {
                //update guessresult with negative message
                guessresult.setTextColor(Color.parseColor(negativeColor));
                guessresult.setText("Incorrect!");
            }
        }
    }

    private void clearGuessText() {
        //hides guesstext when it's no longer relevant to current input (like when a value is changed by the user)
        guessresult.setTextColor(Color.parseColor(transparentColor));
    }


}
