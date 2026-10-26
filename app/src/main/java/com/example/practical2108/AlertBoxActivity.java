package com.example.practical2108;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AlertBoxActivity extends AppCompatActivity {

    Button btnAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java file with XML file
        setContentView(R.layout.alert_box);

        // Connect Button
        btnAlert = findViewById(R.id.btnAlert);

        // Button click
        btnAlert.setOnClickListener(view -> {

            // Create Alert Dialog
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(AlertBoxActivity.this);

            // Set title
            builder.setTitle("Welcome");

            // Set message
            builder.setMessage("Welcome to Android Programming");

            // Add OK button
            builder.setPositiveButton("OK", null);

            // Show AlertBox
            builder.show();
        });
    }
}