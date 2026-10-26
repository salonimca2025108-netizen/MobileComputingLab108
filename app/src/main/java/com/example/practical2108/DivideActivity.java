package com.example.practical2108;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DivideActivity extends AppCompatActivity {

    EditText edtNum1, edtNum2;
    Button btnDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.divide_layout);

        // Connect EditTexts
        edtNum1 = findViewById(R.id.edtNum1);
        edtNum2 = findViewById(R.id.edtNum2);

        // Connect Button
        btnDivide = findViewById(R.id.btnDivide);

        // Divide Button Click
        btnDivide.setOnClickListener(view -> {

            // Check if fields are empty
            if (edtNum1.getText().toString().trim().isEmpty()
                    || edtNum2.getText().toString().trim().isEmpty()) {

                new AlertDialog.Builder(DivideActivity.this)
                        .setTitle("Error")
                        .setMessage("Please enter both numbers")
                        .setPositiveButton("OK", null)
                        .show();

                return;
            }

            // Get first number
            double num1 = Double.parseDouble(
                    edtNum1.getText().toString()
            );

            // Get second number
            double num2 = Double.parseDouble(
                    edtNum2.getText().toString()
            );

            // Check for division by zero
            if (num2 == 0) {

                // Show Error AlertBox
                new AlertDialog.Builder(DivideActivity.this)
                        .setTitle("Error")
                        .setMessage("Cannot divide by zero")
                        .setPositiveButton("OK", null)
                        .show();

            } else {

                // Perform division
                double result = num1 / num2;

                // Show Result AlertBox
                new AlertDialog.Builder(DivideActivity.this)
                        .setTitle("Result")
                        .setMessage("Result = " + result)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
}