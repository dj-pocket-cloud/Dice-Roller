package com.example.diceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing the tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView sidevalue1;
    private TextView probability_value;
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private int sides = 6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        probability_value = (TextView) findViewById(R.id.probability_value);
        sidevalue1 = (TextView) findViewById(R.id.sidevalue1);

        SeekBar sidebar1 = (SeekBar) findViewById(R.id.sidebar1);
        sidebar1.setOnSeekBarChangeListener(seekBarListener);
        sidesChanged();
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
}
