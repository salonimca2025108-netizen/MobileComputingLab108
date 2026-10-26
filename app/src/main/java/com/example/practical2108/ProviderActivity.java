package com.example.practical2108;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProviderActivity extends AppCompatActivity {

    EditText edtName;
    Button btnInsert, btnAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.provider_activity);

        edtName = findViewById(R.id.edtName);
        btnInsert = findViewById(R.id.btnInsert);
        btnAccess = findViewById(R.id.btnAccess);

        // INSERT STUDENT
        btnInsert.setOnClickListener(v -> {

            String name =
                    edtName.getText().toString().trim();

            if (name.isEmpty()) {

                edtName.setError(
                        "Enter Student Name"
                );

                return;
            }

            ContentValues values =
                    new ContentValues();

            values.put("name", name);

            getContentResolver().insert(
                    StudentProvider.CONTENT_URI,
                    values
            );

            Toast.makeText(
                    this,
                    "Student Inserted Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            edtName.setText("");
        });

        // ACCESS DATA
        btnAccess.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ProviderActivity.this,
                    ConsumerActivity.class
            );

            startActivity(intent);
        });
    }
}