package com.example.vishniya.checkboxesandetc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

/*
Q1 The difference between checkboxes and radiogroups is...
... that radiogroups only allow one box to be ticked at a time, if you click another box all others are unchecked
Q2 A LinearLayout can be used to align a set of checkboxes vertically

Q3 For radiobuttons you use a checkable interface to check the state of a radio button
.. the method inside the interface that youll use is "isChecked"


 */


public class MainActivity extends AppCompatActivity  {

    private ArrayList<String> toastText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toastText = new ArrayList<>();
    }

    public void showToast(View view) {

        String message = toastText.toString();

        Toast.makeText(getApplicationContext(),  message,Toast.LENGTH_LONG).show();
        System.out.println(message);
    }
/*
    @Override
    public void onItemSelected(@org.jetbrains.annotations.NotNull AdapterView<?> adapterView, View view, int position, long id) {
        toastText.add( adapterView.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
*/
    public void  onCheckboxChecked(View view) {
      boolean checked = ((CheckBox) view).isChecked();

      if (checked) {
          toastText.add(((CheckBox) view).getText().toString());
      }
    else return;
    }
}
