package com.example.vishniya.twoactivites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
        Log.d(LOG_TAG, "---------");
        Log.d(LOG_TAG, "onStart");


    }



    // this method dictates what's preserved when this activity is destroyed
    // editText contents are automatically state-saved
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // here im checking if the header is currently visible (i.e  there is data that we need to save ), if so I'm adding a boolean "true" to the outState bundle (this bundle is saved when an activity is destroyed)
        // and then also save the text from the replyText since we know there is some
        if(mReplyHeadTextView.getVisibility()==View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("reply_text", mReplyTextView.getText().toString());
        }

    }

    // this recalls preserved instance states (u can also just pout it in "onCreate" instead - that's usually better and faster
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState !=null){
            // here we retreive the boolean from the bundle
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if(isVisible) {
                // restore the header
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                // restore the replyTextView
                String replyText = savedInstanceState.getString("reply_text");
                mReplyTextView.setText(replyText);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
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
