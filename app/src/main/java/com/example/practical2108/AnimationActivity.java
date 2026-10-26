package com.example.practical2108;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnFade, btnRotate, btnBlink, btnMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation);

        imageView = findViewById(R.id.imageView);

        btnFade = findViewById(R.id.btnFade);
        btnRotate = findViewById(R.id.btnRotate);
        btnBlink = findViewById(R.id.btnBlink);
        btnMove = findViewById(R.id.btnMove);

        // Fade Animation
        btnFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlphaAnimation fade =
                        new AlphaAnimation(1.0f, 0.0f);

                fade.setDuration(1000);
                fade.setRepeatCount(1);
                fade.setRepeatMode(Animation.REVERSE);

                imageView.startAnimation(fade);
            }
        });

        // Rotate Animation
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RotateAnimation rotate =
                        new RotateAnimation(
                                0,
                                360,
                                Animation.RELATIVE_TO_SELF,
                                0.5f,
                                Animation.RELATIVE_TO_SELF,
                                0.5f
                        );

                rotate.setDuration(1000);
                rotate.setRepeatCount(1);

                imageView.startAnimation(rotate);
            }
        });

        // Blink Animation
        btnBlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlphaAnimation blink =
                        new AlphaAnimation(1.0f, 0.0f);

                blink.setDuration(300);
                blink.setRepeatCount(5);
                blink.setRepeatMode(Animation.REVERSE);

                imageView.startAnimation(blink);
            }
        });

        // Move Animation
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TranslateAnimation move =
                        new TranslateAnimation(
                                0,
                                300,
                                0,
                                0
                        );

                move.setDuration(1000);
                move.setRepeatCount(1);
                move.setRepeatMode(Animation.REVERSE);

                imageView.startAnimation(move);
            }
        });
    }
}