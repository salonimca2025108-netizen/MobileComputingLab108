package com.example.practical2108;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ScaleAnimation extends Activity {

    ImageView imageView;
    Button btnScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with scale_animation.xml
        setContentView(R.layout.scale_animation);

        // Connect ImageView and Button
        imageView = findViewById(R.id.imageView);
        btnScale = findViewById(R.id.btnScale);

        // Execute animation when button is clicked
        btnScale.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Load scale animation
                Animation animation =
                        AnimationUtils.loadAnimation(
                                ScaleAnimation.this,
                                R.anim.scale
                        );

                // Start animation on ImageView
                imageView.startAnimation(animation);
            }
        });
    }
}