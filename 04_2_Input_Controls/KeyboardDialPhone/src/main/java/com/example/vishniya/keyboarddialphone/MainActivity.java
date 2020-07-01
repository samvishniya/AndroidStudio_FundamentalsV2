package com.example.vishniya.keyboarddialphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        if(editText!= null)
            editText.setOnEditorActionListener(
                    new TextView.OnEditorActionListener(){
// this sets a listener for the edittext if any key is pressed
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            boolean handled = false;
                            if (actionId == EditorInfo.IME_ACTION_SEND) {
                                dialNumber();
                                handled=true;
                            }
                            return handled;
                        }
                    }
            );

    }

    private void dialNumber() {
// get phone number
        EditText editText = findViewById(R.id.editText);
        String phoneNum = null;
        if(editText!= null) {
            phoneNum = "tel:" + editText.getText().toString();
        }


// create implicit intent
        Intent intent = new Intent(Intent.ACTION_DIAL);
        // set data for implicit intent as phone number

        intent.setData(Uri.parse(phoneNum));


        if (intent.resolveActivity(getPackageManager())!= null) {
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "cant handle this!");
        }
    }
}
