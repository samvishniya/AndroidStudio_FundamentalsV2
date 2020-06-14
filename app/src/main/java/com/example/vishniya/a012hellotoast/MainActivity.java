package com.example.vishniya.a012hellotoast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;




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
