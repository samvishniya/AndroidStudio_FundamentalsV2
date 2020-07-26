package com.example.android.hellosharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

// navigationframework gradle library fixes issue with spinner potentially
public class SharedPreferencesManager extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";

    private SharedPreferences preferences;
    private String sharedPrefFile = "com.example.android.samepackagenameasapp";
    SharedPreferences.Editor preferencesEditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_manager);
        preferences=getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        preferencesEditor = preferences.edit();




        Spinner colorSpinner = findViewById(R.id.color_spinner);
        if(colorSpinner!=null){
            colorSpinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.color_spinner_labels_array , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(colorSpinner != null){
            colorSpinner.setAdapter(adapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String spinnerLabel=adapterView.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);
        int color=R.color.default_background;


        switch (spinnerLabel){

            case "Red":
                color=R.color.red_background;
                break;
            case "Blue":
                color=R.color.blue_background;
            case "Green":
                color=R.color.green_background;
        }

        preferencesEditor.putInt(COLOR_KEY,color);
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void displayToast(String spinnerLabel) {
        Toast.makeText(this,spinnerLabel,Toast.LENGTH_SHORT).show();
    }


}