package com.example.android.materialme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = findViewById(R.id.titledetail);
        TextView infoDetail = findViewById(R.id.infodetail);
        ImageView banner = findViewById(R.id.imageView_bannerdetail);

        Intent intent = getIntent();
        String titleString = intent.getStringExtra("TITLE");
        String infoString = intent.getStringExtra("INFODETAIL");
        int imageInt = intent.getIntExtra("BANNER", 0);




        title.setText(titleString);
        infoDetail.setText(infoString);
        Glide.with(this).load(imageInt).into(banner);
    }
}
