package com.example.practical2108;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {

    EditText edtName;

    Button btnSave, btnDisplay;

    TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.shared_preferences);


        // Connect XML components
        edtName = findViewById(R.id.edtName);

        btnSave = findViewById(R.id.btnSave);

        btnDisplay = findViewById(R.id.btnDisplay);

        txtResult = findViewById(R.id.txtResult);


        // ==========================================
        // SAVE DATA
        // ==========================================

        btnSave.setOnClickListener(view -> {

            // Get name entered by user
            String name =
                    edtName.getText().toString().trim();


            // Check if name is empty
            if (name.isEmpty()) {

                txtResult.setText(
                        "Please enter student name"
                );

                return;
            }


            // Open/Create SharedPreferences
            getSharedPreferences(
                    "StudentData",
                    MODE_PRIVATE
            )

                    // Open editor
                    .edit()

                    // Store student name
                    .putString(
                            "student_name",
                            name
                    )

                    // Save changes
                    .apply();


            // Display message
            txtResult.setText(
                    "Data Saved"
            );
        });


        // ==========================================
        // DISPLAY DATA
        // ==========================================

        btnDisplay.setOnClickListener(view -> {

            // Open SharedPreferences
            String name =
                    getSharedPreferences(
                            "StudentData",
                            MODE_PRIVATE
                    )

                            // Retrieve stored name
                            .getString(
                                    "student_name",
                                    "No Data Found"
                            );


            // Display retrieved data
            txtResult.setText(
                    "Student Name: " + name
            );
        });
    }
}