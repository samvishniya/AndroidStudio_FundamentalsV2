package com.example.vishniya.a012hellotoast;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView displayGreeting;
    TextView displayCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String greeting = intent.getStringExtra(MainActivity.GREETING);
        int count = intent.getIntExtra(MainActivity.COUNT, 0);


        displayGreeting=findViewById(R.id.text_greeting);
        displayCount=findViewById(R.id.text_count);

        displayGreeting.setText(greeting);
        // remember to convert to string as below, settext doesnt work with non-strings
        displayCount.setText(String.valueOf(count));


    }

}
