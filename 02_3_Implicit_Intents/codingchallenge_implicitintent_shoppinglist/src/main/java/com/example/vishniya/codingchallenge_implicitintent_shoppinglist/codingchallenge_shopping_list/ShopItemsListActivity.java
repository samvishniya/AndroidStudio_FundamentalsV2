package com.example.vishniya.codingchallenge_implicitintent_shoppinglist.codingchallenge_shopping_list;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vishniya.codingchallenge_shopping_list.R;

public class ShopItemsListActivity extends AppCompatActivity {

    private static final String LOG_TAG = ShopItemsListActivity.class.getSimpleName();
    public static final String EXTRA_SHOP_ITEM = "com.example.vishniya.codingchallenge_shopping_list.extra.shop.item";

    EditText locationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_items_list);

        locationEditText=findViewById(R.id.editText_find_store);

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


    public void findLocationOnMap(View view) {

        if ( locationEditText != null) {
            String location = locationEditText.getText().toString();
            Uri locationUri = Uri.parse("geo:0,0?q=" + location);


            Intent intent = new Intent(Intent.ACTION_VIEW,locationUri);

            if(intent.resolveActivity(getPackageManager())!=null){
                startActivity(intent);
            }
            else {
                Log.d("LOG_TAG", "No app found to handle this");
            }

        }
        else {
            Log.d("LOG_TAG", "No location found to handle this");
        }
    }
}
