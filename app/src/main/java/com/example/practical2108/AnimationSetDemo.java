package com.example.practical2108;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationSetDemo extends Activity {

    ImageView imageView;
    Button btnAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with animation_set_layout.xml
        setContentView(R.layout.animation_set_layout);

        // Connect ImageView and Button
        imageView = findViewById(R.id.imageView);
        btnAnimate = findViewById(R.id.btnAnimate);

        // Execute animation when button is clicked
        btnAnimate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Load AnimationSet XML
                Animation animation =
                        AnimationUtils.loadAnimation(
                                AnimationSetDemo.this,
                                R.anim.animation_set
                        );

                // Start animation on ImageView
                imageView.startAnimation(animation);
            }
        });
    }
}