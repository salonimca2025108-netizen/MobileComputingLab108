package com.example.practical2108;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseCrudActivity extends AppCompatActivity {

    EditText edtId, edtName;

    Button btnRead, btnUpdate, btnDelete;

    TextView txtResult;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.firebase_crud);


        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);

        btnRead = findViewById(R.id.btnRead);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        txtResult = findViewById(R.id.txtResult);


        databaseReference =
                FirebaseDatabase
                        .getInstance()
                        .getReference("students");


        // =========================
        // READ DATA
        // =========================

        btnRead.setOnClickListener(v -> {

            databaseReference
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {

                                @Override
                                public void onDataChange(
                                        @NonNull DataSnapshot snapshot) {

                                    StringBuilder data =
                                            new StringBuilder();


                                    for (DataSnapshot studentSnapshot :
                                            snapshot.getChildren()) {

                                        Student student =
                                                studentSnapshot
                                                        .getValue(Student.class);


                                        if (student != null) {

                                            data.append("ID: ")
                                                    .append(student.getId())
                                                    .append("\n");

                                            data.append("Name: ")
                                                    .append(student.getName())
                                                    .append("\n\n");
                                        }
                                    }


                                    if (data.length() == 0) {

                                        txtResult.setText(
                                                "No Student Data Found"
                                        );

                                    } else {

                                        txtResult.setText(
                                                data.toString()
                                        );
                                    }
                                }


                                @Override
                                public void onCancelled(
                                        @NonNull DatabaseError error) {

                                    Toast.makeText(
                                            FirebaseCrudActivity.this,
                                            "Read Failed",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            });
        });


        // =========================
        // UPDATE DATA
        // =========================

        btnUpdate.setOnClickListener(v -> {

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
                        "Enter ID and New Name",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            databaseReference
                    .child(id)
                    .child("name")
                    .setValue(name)

                    .addOnSuccessListener(unused -> {

                        Toast.makeText(
                                this,
                                "Student Updated Successfully",
                                Toast.LENGTH_SHORT
                        ).show();

                        readAllStudents();
                    })

                    .addOnFailureListener(error -> {

                        Toast.makeText(
                                this,
                                "Update Failed",
                                Toast.LENGTH_SHORT
                        ).show();
                    });
        });


        // =========================
        // DELETE DATA
        // =========================

        btnDelete.setOnClickListener(v -> {

            String id =
                    edtId.getText()
                            .toString()
                            .trim();


            if (id.isEmpty()) {

                Toast.makeText(
                        this,
                        "Enter Student ID",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            databaseReference
                    .child(id)
                    .removeValue()

                    .addOnSuccessListener(unused -> {

                        Toast.makeText(
                                this,
                                "Student Deleted Successfully",
                                Toast.LENGTH_SHORT
                        ).show();

                        edtId.setText("");
                        edtName.setText("");

                        readAllStudents();
                    })

                    .addOnFailureListener(error -> {

                        Toast.makeText(
                                this,
                                "Delete Failed",
                                Toast.LENGTH_SHORT
                        ).show();
                    });
        });
    }


    // ======================================
    // METHOD TO DISPLAY ALL STUDENTS
    // ======================================

    private void readAllStudents() {

        databaseReference
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {

                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot snapshot) {

                                StringBuilder data =
                                        new StringBuilder();


                                for (DataSnapshot studentSnapshot :
                                        snapshot.getChildren()) {

                                    Student student =
                                            studentSnapshot
                                                    .getValue(Student.class);


                                    if (student != null) {

                                        data.append("ID: ")
                                                .append(student.getId())
                                                .append("\n");

                                        data.append("Name: ")
                                                .append(student.getName())
                                                .append("\n\n");
                                    }
                                }


                                if (data.length() == 0) {

                                    txtResult.setText(
                                            "No Student Data Found"
                                    );

                                } else {

                                    txtResult.setText(
                                            data.toString()
                                    );
                                }
                            }


                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError error) {

                                Toast.makeText(
                                        FirebaseCrudActivity.this,
                                        "Unable to read data",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        });
    }
}