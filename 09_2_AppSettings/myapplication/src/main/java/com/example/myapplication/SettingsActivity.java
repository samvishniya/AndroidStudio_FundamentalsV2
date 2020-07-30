package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;


// this is an empty class to hold the settingsfragment
public class SettingsActivity extends AppCompatActivity {

    // key value for the switch - to use with sharedpreferences file
    public static final String KEY_PREF_EXAMPLE_SWITCH = "example_switch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // this makes the fragment display as the main content (inflates it)
        // use getFragmentManager if the class extends activity and the fragment extends preffrag
        // (or in this case)) use getSupportFragMan when the class extends apcompat and the frag extends preffragcompat
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();



    }
}