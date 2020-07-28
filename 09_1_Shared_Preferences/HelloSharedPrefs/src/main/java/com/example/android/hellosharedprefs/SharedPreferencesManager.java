package com.example.android.hellosharedprefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

// navigationframework gradle library fixes issue with spinner potentially
public class SharedPreferencesManager extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String COUNT_SAVE_KEY ="count_save" ;
    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private int color;

    private final String COLOR_KEY = "color";

    private SharedPreferences preferences;
    private String sharedPrefFile = "com.example.android.samepackagenameasapp";
    SharedPreferences.Editor preferencesEditor;
    Switch countSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_manager);
        preferences=getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        preferencesEditor = preferences.edit();

         countSwitch = findViewById(R.id.countSwitch);



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
         color=R.color.default_background;
        color= R.color.colorAccent;


        switch (spinnerLabel){

            case "Red":
// set colors using ContextCompat.getColor (context,R...adress)
                // this gets a color that uve defined inxml
                color= ContextCompat.getColor(this,R.color.red_background);
                break;
            case "Blue":
                color= ContextCompat.getColor(this,R.color.blue_background);

                break;
            case "Green":
                color= ContextCompat.getColor(this,R.color.green_background);

                break;
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void displayToast(String spinnerLabel) {
        Toast.makeText(this,spinnerLabel,Toast.LENGTH_SHORT).show();
    }

   // save preferences in sharedpreffile
    public void savePreferences(View view) {
        SharedPreferences.Editor preferencesEditor = preferences.edit();

        // put and apply must be in the same method, or it wont beg applied
        preferencesEditor.putInt(COLOR_KEY,color);

        if (countSwitch.isChecked()) {
            preferencesEditor.putBoolean(COUNT_SAVE_KEY, true);
        }
        else{
            preferencesEditor.putBoolean(COUNT_SAVE_KEY,false);
        }


        preferencesEditor.apply();
        Toast.makeText(this, "Settings have been saved!", Toast.LENGTH_LONG).show();

    }

    /**
     * returns settings to default
     * @param view okkk
     */
    public void resetPreferences(View view) {
        SharedPreferences.Editor preferencesEditor = preferences.edit();

        preferencesEditor.clear();
        preferencesEditor.apply();
        // do i need to reset the color and int values stored in the main activit too?
        // or automaticaly reset upon returning to that activity?


    }
}