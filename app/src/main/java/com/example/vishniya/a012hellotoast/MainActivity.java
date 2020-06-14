package com.example.vishniya.a012hellotoast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
Q1 upon adding a 2nd activity to an app: the second activity xml layout file is created , a 2nd activity java class is added and the androidmanifestxml file is chnaged to declare a second activity


Q2 If you remove the android:parentActivityNane and metadata elements from the androidmanifestxml file
... the up button in the app bar no longer appears to send you back to the parent activity

Q3 to create a new explicit intent, you need to use the following constructor method
... new Intent(Context context, Class<?> class)

Q4 In this homework hellotoast app you can add the current value of the count to the intent
...using an intent extra

Q5 In this homework app you can display the current count in the 2nd activity
... by getting the activity that launched the event, getting the count from the getIntExtra, then applying that value to a textview

 */



public class MainActivity extends AppCompatActivity {

    private int mCount;
    private TextView mShowCount;
    public static final String GREETING = "TOAST_GREETING";
    public static final String COUNT = "COUNT";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount= findViewById(R.id.show_count);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this,SecondActivity.class);
        String greeting = "Hello!";
        intent.putExtra(GREETING,greeting);
       intent.putExtra(COUNT, mCount);
        startActivity(intent);

    }


    public void countUp(View view) {
       mCount++;
        if (mShowCount!=null){
            mShowCount.setText(Integer.toString(mCount));
        }
    }
}
