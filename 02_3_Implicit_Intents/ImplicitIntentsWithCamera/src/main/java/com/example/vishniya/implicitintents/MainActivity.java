package com.example.vishniya.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.net.URI;


/*
Q1 to launch a camera app you use new Intent(String action)  (no data required, just the action that coordinates with a camera apps intent filters)

Q2 When you create an implicit intent object you dont specify the thing youre launching,
you add an intent action or intent category /both
you then resolve the intent with the system using intent.resolveActivity(getPackageManager())!=null)
finally you call startactivity or startactivtyforresult (to get data back i.e a photo)

Q3 To take a photo you do Intent takePhoto = new Intent(MediaStore.ACTION....image capture(

 */

public class MainActivity extends AppCompatActivity {

    private EditText websiteEditText;
    private EditText locationEditText;
    private EditText shareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        websiteEditText = findViewById(R.id.website_edittext);
        locationEditText = findViewById(R.id.location_edittext);
        shareEditText = findViewById(R.id.share_edittext);

    }


    // implicit intents send stuff to an activity that has been designated to handle that type of implicit Intent
    // i.e for a URI opener a web browser would have been designed to handle this type of implicit intent
    public void openWebsite(View view) {
        // get text from edittext
        String url = websiteEditText.getText().toString();
        // convert text to uri,
        Uri webpage = Uri.parse(url);
        // create new (implicit) intent with Intent.ACTION_VIEW as the action and the uri as the data
        // note the difference between explicit intent, here you don't state the origin and destination of the intent
        // instead you label it with the type, and then just add the data
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // now use the resolveactivity method and the android package manager to find an activity that can handle your implcit intent
        // (or at least to check you possess an app that can handle the intent action+data)

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("LOG_TAG", "No app found to handle this");
        }

    }

    public void openLocation(View view) {
        String location = locationEditText.getText().toString();
        // here we parse the string into a uri object with a geo search query (those weird symbols added are for the query string in order to get ag general location)
        Uri locationUri = Uri.parse("geo:0,0?q=" + location);

        Intent intent = new Intent(Intent.ACTION_VIEW, locationUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Log.d("LOG_TAG", "No app found to handle this");
        }


    }


    // sharing items from an app is made easier with ShareCompat.IntenBuilder helper class
    public void shareText(View view) {
        String share = shareEditText.getText().toString();

        // define the mime type of the text to share
// a mime type is akin to a file extension in windows, it lets other apps know how to handle the data
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
               .setSubject("subject")
                .setType(mimeType)
                .setChooserTitle(R.string.share_title_text)
                .setText(share)
                .startChooser();


    }

    public void openCamera(View view) {
       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       // Intent intent = new Intent(Intent.ACTION_CAMERA_BUTTON);

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else Log.d("LOG_TAG", "Unable to take a photo");

    }

}
