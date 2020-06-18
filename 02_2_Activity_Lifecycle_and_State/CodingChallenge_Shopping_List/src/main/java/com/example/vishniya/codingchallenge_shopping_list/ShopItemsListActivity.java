package com.example.vishniya.codingchallenge_shopping_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ShopItemsListActivity extends AppCompatActivity {

    private static final String LOG_TAG = ShopItemsListActivity.class.getSimpleName();
    public static final String EXTRA_SHOP_ITEM = "com.example.vishniya.codingchallenge_shopping_list.extra.shop.item";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_items_list);

    }


    public void sendItemToMainActivityList(View view) {
        Button itemButton = (Button) view;
        String itemToBeAddedName = itemButton.getText().toString();

                //getString(R.string.text_shop_item_1);
        Log.d(LOG_TAG, itemToBeAddedName + " - Button Clicked");

        Intent intent = new Intent();
        intent.putExtra(EXTRA_SHOP_ITEM, itemToBeAddedName);
        setResult(RESULT_OK,intent);
        Log.d(LOG_TAG,"ShopItemsListActivity Ended");
        finish();
    }


}
