package com.example.vishniya.a021twoactivites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.vishniya.a021twoactivites.extra.REPLY";
    private EditText mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // getintent gets the intent that started this activity
        Intent intent=getIntent();

        // alternative way to get intent extras
        Bundle firstActivityData = getIntent().getExtras();

        // this gets the stringextra attached to that intent - you use the key to designate which data you're reterieving
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        if (message==null){
            return;
        }

        // this sets the text of the textview to the intent's stringextra
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
    }

    public void returnReply(View view) {
        mReply = findViewById(R.id.editText_second);
        Intent replyIntent = new Intent();
        String reply = mReply.getText().toString();
        replyIntent.putExtra(EXTRA_REPLY,reply);
        setResult(RESULT_OK,replyIntent);
        finish();
    }
}
