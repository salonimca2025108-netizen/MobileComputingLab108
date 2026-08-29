package com.example.practical2108;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ValidateNameActivity extends AppCompatActivity {

    EditText edtName;
    Button btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.validate_name);

        // Connect EditText
        edtName = findViewById(R.id.edtName);

        // Connect Button
        btnValidate = findViewById(R.id.btnValidate);

        // Button Click
        btnValidate.setOnClickListener(view -> {

            // Get name from EditText
            String name = edtName.getText().toString().trim();

            // Check whether name is empty
            if (name.isEmpty()) {

                // Show Error AlertBox
                new AlertDialog.Builder(ValidateNameActivity.this)
                        .setTitle("Error")
                        .setMessage("Please enter your name")
                        .setPositiveButton("OK", null)
                        .show();

            } else {

                // Show Success AlertBox
                new AlertDialog.Builder(ValidateNameActivity.this)
                        .setTitle("Success")
                        .setMessage("Hello " + name)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
}