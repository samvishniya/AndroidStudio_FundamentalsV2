package com.example.myapplication;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;


public class SettingsFragment extends PreferenceFragmentCompat {


    // we dont create a view , instead just createpreferences
    // cos we are adding this settingsfrag to the existing settings activity to display preferences rather than showing a separate fragment screen
    // thisi sbest practice, cos you can easily add or remove a fragment while a fragment is running
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // here we link the preferences.xml file including its key
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}