package com.example.practical2108;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseEntryActivity extends AppCompatActivity {

    EditText edtId, edtName;

    Button btnInsert, btnAccess;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.firebase_entry);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);

        btnInsert = findViewById(R.id.btnInsert);
        btnAccess = findViewById(R.id.btnAccess);


        // Connect to "students" in Firebase
        databaseReference =
                FirebaseDatabase
                        .getInstance()
                        .getReference("students");


        // =========================
        // INSERT STUDENT
        // =========================

        btnInsert.setOnClickListener(v -> {

            String id =
                    edtId.getText()
                            .toString()
                            .trim();

            String name =
                    edtName.getText()
                            .toString()
                            .trim();


            if (id.isEmpty() || name.isEmpty()) {

                Toast.makeText(
                        this,
                        "Enter ID and Name",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            Student student =
                    new Student(id, name);


            // students → id → student data
            databaseReference
                    .child(id)
                    .setValue(student)
                    .addOnSuccessListener(unused -> {

                        Toast.makeText(
                                this,
                                "Student Inserted Successfully",
                                Toast.LENGTH_SHORT
                        ).show();

                        edtId.setText("");
                        edtName.setText("");
                    })

                    .addOnFailureListener(error -> {

                        Toast.makeText(
                                this,
                                "Insertion Failed: "
                                        + error.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    });
        });


        // =========================
        // ACCESS DATA
        // =========================

        btnAccess.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            FirebaseEntryActivity.this,
                            FirebaseCrudActivity.class
                    );

            startActivity(intent);
        });
    }
}