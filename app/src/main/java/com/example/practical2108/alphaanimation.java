package com.example.practical2108;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class alphaanimation extends Activity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with alpha_animation.xml
        setContentView(R.layout.alpha_animation);

        // Connect Button with Java
        button = findViewById(R.id.button);

        // Execute animation when Button is clicked
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Load Alpha Animation
                Animation animation =
                        AnimationUtils.loadAnimation(
                                alphaanimation.this,
                                R.anim.alpha
                        );

                // Start animation on Button
                button.startAnimation(animation);
            }
        });
    }
}