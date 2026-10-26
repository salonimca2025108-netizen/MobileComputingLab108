package com.example.practical2108;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.confirmation_box);

        // Connect Button
        btnConfirm = findViewById(R.id.btnConfirm);

        // Button Click
        btnConfirm.setOnClickListener(view -> {

            // Create AlertDialog
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(ConfirmationActivity.this);

            // Title
            builder.setTitle("Confirmation");

            // Message
            builder.setMessage("Do you want to continue?");

            // Yes Button
            builder.setPositiveButton("Yes", null);

            // No Button
            builder.setNegativeButton("No", null);

            // Show AlertBox
            builder.show();
        });
    }
}