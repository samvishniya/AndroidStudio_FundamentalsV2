package com.example.levellistbattery;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/*
* Q1 : A NinePatchDrawable is ideal for creating a button with a background that stretches properly to accommodate the text/image inside so that it looks correct for diff screen sizes /orientations
* Q2: A StateListDrawable is ideal for a button that changes background/text /style when hovered/clicked
* Q3: If you want a white background, dark text and a dark action bar, use Theme.AppCompat.Light.DarkActionBar
 */

public class MainActivity extends AppCompatActivity {

    ImageView battery;
    ImageButton decrBattery;
    ImageButton incrBattery;
    int batteryLevel=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        battery=findViewById(R.id.batteryImage);
        battery.setImageLevel(batteryLevel);

        decrBattery=findViewById(R.id.decrBattery);
        incrBattery=findViewById(R.id.incrBattery);

    }

    public void decreaseBattery(View view) {
        if(batteryLevel<10) {
            return;
        }
      batteryLevel=  batteryLevel-10;
    battery.setImageLevel(batteryLevel);
    }

    public void increaseBattery(View view) {
        if(batteryLevel>90) {
            return;
        }


        batteryLevel=  batteryLevel+10;

        battery.setImageLevel(batteryLevel);
    }
}
