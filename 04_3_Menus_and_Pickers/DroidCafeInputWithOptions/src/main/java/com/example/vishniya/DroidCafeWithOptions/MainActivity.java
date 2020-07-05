package com.example.vishniya.DroidCafeWithOptions;

import android.content.Intent;
import android.os.Bundle;

import com.example.vishniya.a04_3_Menus_and_Pickers.DroidCafeInputWithOptions.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/*
Q1 To add images to a android studio project .
.. you should put the images into the drawable folder inside your project, then drag an imagebutton to a layout and do new->image asset choosing the image file
Q2 To make a imageview clickable like a button add the android onclick atrtribute and use it to call a click handler in the actrivity

Q3 a click handler claled from the attribute in the layout...
... must be public return void and define a view as its only paaramter


04.3 :

Q1: you create options menu items in a menu_main.xml

Q2: when you click an options menu item
...the method onOptionsItemSelected is called

Q3: The following statement sets the title for an alert dialog...
... myAlertBuilder.setTitle("Alert")

Q4 You create a DialogFragment for a date-pciker ina a fragment that extends dialogFragment, and implements DatePicker.onDateSetListener()
... inside the onCreateDialog() method of that frag

 */






public class MainActivity extends AppCompatActivity {


    private String orderMessage = "";


    public static final String EXTRA_MESSAGE="com.example.vishniya.a04_1_clickable_images.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening order page", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this,OrderActivity.class);


         // also send order message to activity_order to display at top of that activity

                intent.putExtra(EXTRA_MESSAGE, orderMessage );


                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        // this checks which menuitem is selected, and displays a custom toast message
        switch (item.getItemId()) {

            // here we also code action_order to start the orderactivity
            case R.id.action_order:

                displayToast(getString(R.string.action_order_message));
                Intent intent = new Intent(this,OrderActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_status:
                displayToast(getString(R.string.action_status_message));
                return true;
            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites_message));
                return true;
            case R.id.action_contact:
                displayToast(getString(R.string.action_contact_message));
                return true;
            default:
                // Do nothing
        }

        return super.onOptionsItemSelected(item);
    }



    private void displayToast(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    public void showRedIceCreamOrder(View view) {
        orderMessage="Your red ice cream order is on its way";
        displayToast(orderMessage);

    }


}
