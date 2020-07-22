package com.example.android.materialme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

     //   getWindow().setExitTransition(findViewById(R.id.));

        TextView title = findViewById(R.id.titledetail);
        TextView infoDetail = findViewById(R.id.infodetail);
        ImageView banner = findViewById(R.id.imageView_bannerdetail);
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform));


        Intent intent = getIntent();
        String titleString = intent.getStringExtra("TITLE");
        String infoString = intent.getStringExtra("INFODETAIL");
        int imageInt = intent.getIntExtra("BANNER", 0);




        title.setText(titleString);
        infoDetail.setText(infoString);
        Glide.with(this).load(imageInt).into(banner);
    }
}
