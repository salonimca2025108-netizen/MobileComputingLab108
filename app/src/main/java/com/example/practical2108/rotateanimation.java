package com.example.practical2108;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class rotateanimation extends Activity {

    ImageView imageView;
    Button btnRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with rotate_animation.xml
        setContentView(R.layout.rotate_animation);

        // Connect ImageView and Button with Java
        imageView = findViewById(R.id.imageView);
        btnRotate = findViewById(R.id.btnRotate);

        // Execute rotation when button is clicked
        btnRotate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Load Rotate Animation
                Animation animation =
                        AnimationUtils.loadAnimation(
                                rotateanimation.this,
                                R.anim.rotate
                        );

                // Start animation on ImageView
                imageView.startAnimation(animation);
            }
        });
    }
}