package com.example.practical2108;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {

    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.delete_layout);

        // Connect Delete Button
        btnDelete = findViewById(R.id.btnDelete);

        // Delete Button Click
        btnDelete.setOnClickListener(view -> {

            // Create Confirmation AlertBox
            new AlertDialog.Builder(DeleteActivity.this)

                    // Set title
                    .setTitle("Delete Record")

                    // Set confirmation message
                    .setMessage("Are you sure you want to delete?")

                    // Yes button
                    .setPositiveButton("Yes", (dialog, which) -> {

                        // Show success message
                        new AlertDialog.Builder(DeleteActivity.this)
                                .setTitle("Success")
                                .setMessage("Record Deleted")
                                .setPositiveButton("OK", null)
                                .show();
                    })

                    // No button
                    .setNegativeButton("No", null)

                    // Show Confirmation AlertBox
                    .show();
        });
    }
}