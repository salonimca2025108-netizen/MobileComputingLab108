package com.example.practical2108;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class translateanimation extends Activity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with activity_main.xml
        setContentView(R.layout.translate_animation);

        // Connect Button with Java
        button = findViewById(R.id.button);

        // Execute animation when Button is clicked
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Load translate animation
                Animation animation =
                        AnimationUtils.loadAnimation(
                                translateanimation.this,
                                R.anim.translate
                        );

                // Start animation on Button
                button.startAnimation(animation);
            }
        });
    }
}