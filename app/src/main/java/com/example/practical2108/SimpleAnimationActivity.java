package com.example.practical2108;

import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SimpleAnimationActivity extends AppCompatActivity {

    ImageView imgCar;
    Button btnAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java file with XML layout
        setContentView(R.layout.activity_simple_animation);

        // Find ImageView and Button
        imgCar = findViewById(R.id.imgCar);
        btnAnimate = findViewById(R.id.btnAnimate);

        // Start animation when button is clicked
        btnAnimate.setOnClickListener(v -> {

            /*
             * TranslateAnimation moves the image
             * from one position to another.
             *
             * 0 to 500 = horizontal movement
             * 0 to 0 = no vertical movement
             */

            TranslateAnimation animation =
                    new TranslateAnimation(
                            0, 500,
                            0, 0
                    );

            // Animation duration = 2000 milliseconds
            // 2000 milliseconds = 2 seconds
            animation.setDuration(2000);

            // Start animation
            imgCar.startAnimation(animation);
        });
    }
}