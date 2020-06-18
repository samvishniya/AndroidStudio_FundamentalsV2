package com.example.vishniya.homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/*
Q1 If you dont add an override onsaveinstancestate , only editText preserves its state
Q2 When a device config chagne happens e.g. rotation, android calls onpause, onstop and then ondestroy, and starts it over again calling oncreate, onstart and onresume( you can customise how it restarts)
Q3 !!!!!!onsaveInstancestate is called just before the onstop method !!!!
Q4 if you want to save data before an acitivty ends or is destroyed you should alter the methods of onpause or onstop

 */


public class MainActivity extends AppCompatActivity {

    private TextView counter;
    private int currentCount;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.text_counter);
        editText = findViewById(R.id.editText_counter);
    }

    public void incrementCounter(View view) {
        currentCount++;
        if (counter != null) {
            counter.setText(Integer.toString(currentCount));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        if (currentCount!=(0)) {
            outState.putInt("count", currentCount);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            currentCount=savedInstanceState.getInt("count");
            counter.setText(Integer.toString(currentCount));
        }
    }
}
