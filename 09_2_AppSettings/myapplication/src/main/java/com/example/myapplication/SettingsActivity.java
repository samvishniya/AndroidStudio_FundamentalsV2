package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;


// this is an empty class to hold the settingsfragment
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // this makes the fragment display as the main content
        // use getFragmentManager if the class extends activity and the fragment extends preffrag
        // (or in this case)) use getSupportFragMan when the class extends apcompat and the frag extends preffragcompat
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content,new SettingsFragment())
                .commit();
    }
}