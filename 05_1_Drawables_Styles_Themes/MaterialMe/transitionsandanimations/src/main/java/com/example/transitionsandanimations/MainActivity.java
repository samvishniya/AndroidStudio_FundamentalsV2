package com.example.transitionsandanimations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Scene;
import androidx.transition.Transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import androidx.transition.TransitionManager;

import android.transition.Explode;
import androidx.transition.Fade;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    public static final String ANIMATION = "Animation";
    public static final String EXPLODE_ANIMATION = "Explode Animation";
    public static final String FADE_TRANSITION = "Fade Transition";
    ImageView mYellowSquare;
    ImageView mGreenAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mYellowSquare = findViewById(R.id.image_square);
        mGreenAndroid = findViewById(R.id.image_android);

        ViewGroup viewGroup = findViewById(R.id.constraintView);

        if (getIntent().hasExtra(ANIMATION)) {
            switch (getIntent().getStringExtra(ANIMATION)) {
                case EXPLODE_ANIMATION:
                    android.transition.Explode explode = new android.transition.Explode();
                    getWindow().setEnterTransition(explode);
                    break;
                case FADE_TRANSITION:
                    Fade fade = new Fade();
                    TransitionManager.beginDelayedTransition(view, fade);
                    mGreenAndroid.setVisibility(View.INVISIBLE);
                    break;
                default: break;
            }
        }

    }


    public void animationsAndTransitions(View view) {
        switch (view.getId()) {
            case R.id.image_circle:
                Intent intent = new Intent(this, MainActivity.class);
                android.transition.Explode explode = new Explode();
                explode.setDuration(400);
                getWindow().setExitTransition(explode);
                intent.putExtra(ANIMATION, EXPLODE_ANIMATION);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                startActivity(intent, options.toBundle());
                break;

            case R.id.image_line:
                Intent intent2 = new Intent(this, MainActivity.class);
                intent2.putExtra(ANIMATION, FADE_TRANSITION);
                Fade fade = new Fade();
                fade.setDuration(400);
                getWindow().setExitTransition(fade);
                startActivity(intent2,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;



            default:
                break;
        }
    }

}