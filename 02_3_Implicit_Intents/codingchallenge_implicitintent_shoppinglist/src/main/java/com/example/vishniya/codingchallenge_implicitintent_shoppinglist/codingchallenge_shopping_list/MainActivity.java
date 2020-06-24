package com.example.vishniya.codingchallenge_implicitintent_shoppinglist.codingchallenge_shopping_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.vishniya.codingchallenge_shopping_list.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int SHOP_ITEM_REQUEST = 1;
    private int nextEmptyListEntryID=0;
    private ArrayList <TextView> listTextArray = new ArrayList<TextView>();
    private int listSize =6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeTextViewArray();
    }


    // Alternatively you can pass a whole arraylist as a bundle
    // getStringArrayList
    //

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        for(TextView listItem: listTextArray){
            if(listItem.getVisibility()==View.VISIBLE){
                String itemName = listItem.getText().toString();
               int ID = listItem.getId();
                outState.putBoolean("item_visible"+ID, true);
                outState.putString("item_text"+ID, itemName);
            }
        }

    }


    // this recalls preserved instance states (u can also just pout it in "onCreate" instead - that's usually better and faster
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState !=null){
            // here we retreive the boolean from the bundle
            for(TextView listItem:listTextArray){
                int ID = listItem.getId();
            boolean isVisible = savedInstanceState.getBoolean("item_visible"+ID);
            if(isVisible) {
          // restore the TextView
                String itemName = savedInstanceState.getString("item_text"+ID);
                listItem.setText(itemName);
                listItem.setVisibility(View.VISIBLE);
            }
        }
        }
    }


    private void initializeTextViewArray(){
        /*for(int i=0; i<=listSize;i++) {

            String viewName = "listText"+ Integer.toString(i);
            TextView listText = R.id.viewName;
            listTextArray.add((TextView) findViewById(R.id.viewName));
        }*/

        listTextArray.add((TextView) findViewById(R.id.listText1));
        listTextArray.add((TextView) findViewById(R.id.listText2));
        listTextArray.add((TextView) findViewById(R.id.listText3));
        listTextArray.add((TextView) findViewById(R.id.listText4));
        listTextArray.add((TextView) findViewById(R.id.listText5));
        listTextArray.add((TextView) findViewById(R.id.listText6));
        listTextArray.add((TextView) findViewById(R.id.listText7));
        listTextArray.add((TextView) findViewById(R.id.listText8));
        listTextArray.add((TextView) findViewById(R.id.listText9));
        listTextArray.add((TextView) findViewById(R.id.listText10));


    }

    public void launchSecondActivity(View view) {

        Log.d(LOG_TAG, "Add Item - Button Clicked");


        Intent intent = new Intent(this, ShopItemsListActivity.class);

        startActivityForResult(intent, SHOP_ITEM_REQUEST);


    }
// alternatively can create an array of strings for the item names , adding a string each time you add an item from the shop
    // the
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    if(requestCode==SHOP_ITEM_REQUEST) {
        Log.d(LOG_TAG, "correct request Code");
        if (resultCode==RESULT_OK){
            String itemName = data.getStringExtra(ShopItemsListActivity.EXTRA_SHOP_ITEM);
    Log.d(LOG_TAG,"Result OK ");
            for(int i=1 ; i<=listSize;i++){
                if (listTextArray.get(i).getVisibility()!=View.VISIBLE){
                    listTextArray.get(i).setVisibility(View.VISIBLE);
                    listTextArray.get(i).setText(itemName);
                    return;
                }
            }


        }

    }

    }






}
