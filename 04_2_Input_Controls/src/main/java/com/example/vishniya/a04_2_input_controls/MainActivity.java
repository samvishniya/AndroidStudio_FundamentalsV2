package com.example.vishniya.a04_2_Input_Controls;

import android.content.Intent;
import android.os.Bundle;

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
