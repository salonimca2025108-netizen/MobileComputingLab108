package com.example.practical2108;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SDCardActivity extends AppCompatActivity {

    EditText edtName, edtRollNo;

    Button btnCheck, btnSave;

    TextView txtResult;


    // Storage Access Framework file picker
    private final ActivityResultLauncher<Intent> createFileLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            Uri uri = result.getData().getData();

                            if (uri != null) {
                                saveDataToUri(uri);
                            }
                        }
                    }
            );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.sd_card);


        // Connect XML components
        edtName = findViewById(R.id.edtName);
        edtRollNo = findViewById(R.id.edtRollNo);

        btnCheck = findViewById(R.id.btnCheck);
        btnSave = findViewById(R.id.btnSave);

        txtResult = findViewById(R.id.txtResult);


        // ==========================================
        // CHECK SD CARD / EXTERNAL STORAGE
        // ==========================================

        btnCheck.setOnClickListener(view -> {

            String state =
                    Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {

                txtResult.setText(
                        "External storage is available"
                );

            } else {

                txtResult.setText(
                        "External storage is not available"
                );
            }
        });


        // ==========================================
        // SAVE DATA USING SAF
        // ==========================================

        btnSave.setOnClickListener(view -> {

            // Get student name
            String name =
                    edtName.getText().toString().trim();

            // Get roll number
            String rollNo =
                    edtRollNo.getText().toString().trim();


            // Check empty fields
            if (name.isEmpty() || rollNo.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please enter student name and roll number",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            // Create Storage Access Framework Intent
            Intent intent =
                    new Intent(Intent.ACTION_CREATE_DOCUMENT);

            // Allow user to select a storage location
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            // File type
            intent.setType("text/plain");

            // Default file name
            intent.putExtra(
                    Intent.EXTRA_TITLE,
                    "student.txt"
            );


            // Open Android file picker
            createFileLauncher.launch(intent);
        });
    }


    // ==========================================
    // SAVE DATA TO SELECTED LOCATION
    // ==========================================

    private void saveDataToUri(Uri uri) {

        String name =
                edtName.getText().toString().trim();

        String rollNo =
                edtRollNo.getText().toString().trim();


        String data =
                "Student Name: " + name
                        + "\nRoll Number: " + rollNo;


        try {

            // Open selected file
            OutputStream outputStream =
                    getContentResolver()
                            .openOutputStream(uri);


            if (outputStream != null) {

                // Write data
                outputStream.write(
                        data.getBytes(StandardCharsets.UTF_8)
                );

                // Close file
                outputStream.close();


                // Display result
                txtResult.setText(
                        "Data saved successfully"
                );


                // Toast message
                Toast.makeText(
                        this,
                        "Data Saved Successfully",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}