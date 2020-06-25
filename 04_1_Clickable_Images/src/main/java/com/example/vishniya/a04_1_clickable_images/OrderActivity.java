package com.example.vishniya.a04_1_clickable_images;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    private String orderMessage;
    private TextView orderHeaderTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        Intent intent = getIntent();

        orderMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        orderHeaderTextView = findViewById(R.id.order_message_textview);

        orderHeaderTextView.setText(orderMessage);

    }
}
