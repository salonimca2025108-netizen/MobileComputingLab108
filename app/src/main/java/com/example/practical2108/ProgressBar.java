package com.example.practical2108;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBar extends AppCompatActivity {

    android.widget.ProgressBar progressBar;

    Button btnStart, btnReset;

    TextView txtProgress, txtStatus;

    int progress = 0;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.progress_bar);

        // Connect XML components
        progressBar = findViewById(R.id.progressBar);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        txtProgress = findViewById(R.id.txtProgress);
        txtStatus = findViewById(R.id.txtStatus);


        // Start Download
        btnStart.setOnClickListener(v -> {

            progress = 0;

            progressBar.setProgress(0);

            txtProgress.setText("0%");

            txtStatus.setText("Downloading...");

            // Disable Start button
            btnStart.setEnabled(false);

            handler.postDelayed(new Runnable() {

                @Override
                public void run() {

                    if (progress <= 100) {

                        // Update ProgressBar
                        progressBar.setProgress(progress);

                        // Update percentage
                        txtProgress.setText(progress + "%");


                        // Status messages
                        if (progress < 50) {

                            txtStatus.setText("Downloading...");

                        } else if (progress < 100) {

                            txtStatus.setText("Almost completed...");

                        }


                        // When download is completed
                        if (progress == 100) {

                            txtStatus.setText("Download Completed!");

                            txtProgress.setText("100%");

                            // Enable Start button
                            btnStart.setEnabled(true);

                            return;
                        }


                        // Increase progress
                        progress = progress + 10;

                        // Run again after 500 milliseconds
                        handler.postDelayed(this, 500);
                    }

                }

            }, 500);
        });


        // Reset Button
        btnReset.setOnClickListener(v -> {

            // Stop pending progress
            handler.removeCallbacksAndMessages(null);

            progress = 0;

            progressBar.setProgress(0);

            txtProgress.setText("0%");

            txtStatus.setText("Ready to Download");

            btnStart.setEnabled(true);
        });
    }
}