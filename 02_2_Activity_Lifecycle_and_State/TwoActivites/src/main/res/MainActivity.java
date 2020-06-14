package com.example.vishniya.a021twoactivites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // this constant uses the name of the class itself as a tag
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText mMessageEditText;
    // todo fix thisd 'extra' problem

    // I DONT UNDERSTAND THIS EXTRA MESSAGE THING...
    public static final String EXTRA_MESSAGE = "THIS IS MY MESSAGE IM SENDING OK";
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

    }


    public void LaunchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        // this is an explicit intent, the specific component destination of the intent is explicity stated akak secondactivity
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();

        // putextra sends data along with an intent, it comes in a key-value pair - here EXTRA_MESSAGE is th ekey, message is the data
        // why did we have to declare the key at start of activity? convention? what do we intialize the key as?
        intent.putExtra(EXTRA_MESSAGE, message);

        // use startactivity if you dont expect to get any data back
        // otherwise use startActivityForResult() -- this will handle the returning data e..g adding a sent string to a textview
        // youll use a request key i guess in order to make sure youre sending the right dstuff?
        startActivityForResult(intent, 1);


    }

    // this handles the return data from the other activity
    // requestcode is the key , code is either OK or cancelled (signalled by 2nd activity) and the intent holding the data
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // this tests for the correct Intent result, in case youve got several, and tests that resultCode is RESULT_OK i.e request was succesful

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

}
