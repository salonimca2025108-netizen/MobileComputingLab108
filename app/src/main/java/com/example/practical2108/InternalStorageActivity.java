package com.example.practical2108;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InternalStorageActivity extends AppCompatActivity {

    EditText edtName, edtRollNo;
    Button btnSave, btnRead;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.internal_storage);

        // Connect XML components
        edtName = findViewById(R.id.edtName);
        edtRollNo = findViewById(R.id.edtRollNo);

        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);

        txtResult = findViewById(R.id.txtResult);


        // ==========================================
        // SAVE DATA
        // ==========================================

        btnSave.setOnClickListener(view -> {

            // Get student name
            String name = edtName.getText().toString().trim();

            // Get roll number
            String rollNo = edtRollNo.getText().toString().trim();


            // Check empty fields
            if (name.isEmpty() || rollNo.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please enter all details",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            // Data to save
            String data =
                    "Student Name: " + name +
                            "\nRoll Number: " + rollNo;


            try {

                // Create/Open file in Internal Storage
                FileOutputStream fos =
                        openFileOutput(
                                "student.txt",
                                MODE_PRIVATE
                        );

                // Write data into file
                fos.write(data.getBytes());

                // Close file
                fos.close();


                // Show success message
                Toast.makeText(
                        this,
                        "Data Saved Successfully",
                        Toast.LENGTH_SHORT
                ).show();

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "Error while saving data",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        // ==========================================
        // READ DATA
        // ==========================================

        btnRead.setOnClickListener(view -> {

            try {

                // Open student.txt
                FileInputStream fis =
                        openFileInput("student.txt");

                StringBuilder data =
                        new StringBuilder();

                int ch;

                // Read file character by character
                while ((ch = fis.read()) != -1) {

                    data.append((char) ch);
                }

                // Close file
                fis.close();


                // Display saved data
                txtResult.setText(data.toString());

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "No data found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}