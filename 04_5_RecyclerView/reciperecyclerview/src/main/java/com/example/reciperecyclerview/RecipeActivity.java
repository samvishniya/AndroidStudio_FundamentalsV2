package com.example.reciperecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = getIntent();

        String recipeName = null;
        if (intent.getStringExtra(MainActivity.TAG)!=null) {
             recipeName = intent.getStringExtra(MainActivity.TAG);
        }

        TextView heading = findViewById(R.id.food_name_textview);
        ImageView photo = findViewById(R.id.food_photo_imageview);
        heading.setText(recipeName);
        if (recipeName != null) {
            switch (recipeName){
                case "Chocolate Mint Bars":
                    photo.setImageResource(R.drawable.chocolate_mint_bar);
                    break;
                case "Blueberry Cupcakes":
                    photo.setImageResource(R.drawable.blueberry_cupcakes);
                    break;
                case "Fudge Walnut Brownies":
                    photo.setImageResource(R.drawable.fudge_brownies);
                    break;
                case "Lemon Cake":
                    photo.setImageResource(R.drawable.lemon_cake);
                    break;
                case "Blueberry Peach Cobbler":
                    photo.setImageResource(R.drawable.cobbler);
                    break;
                case "Texas Sheet Cake":
                    photo.setImageResource(R.drawable.sheet_cake);
                    break;
                case "Espresso Crinkles":
                    photo.setImageResource(R.drawable.espresso_crinkles);
                    break;
                case "Chocolate Cherry Cookies":
                    photo.setImageResource(R.drawable.chocolate_cherry_cookies);
                    break;
                case "Vanilla Cheesecake":
                    photo.setImageResource(R.drawable.cheesecake);
                    break;
                case "Tiramisu":
                    photo.setImageResource(R.drawable.tiramisu);
                    break;
                case "Carrot Cake":
                    photo.setImageResource(R.drawable.carrot_cake);
                    break;
                case "Blueberry Ice Cream":
                    photo.setImageResource(R.drawable.blueberry_ice_cream);
                    break;
                default: break;
            }
        }
    }
}
