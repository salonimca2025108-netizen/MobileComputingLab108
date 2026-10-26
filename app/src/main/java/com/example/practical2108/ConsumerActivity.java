package com.example.practical2108;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConsumerActivity extends AppCompatActivity {

    EditText edtId, edtName;
    Button btnRead, btnUpdate, btnDelete;
    TextView txtResult;

    private static final Uri STUDENT_URI =
            Uri.parse(
                    "content://com.example.practical2108.provider/students"
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.consumer_activity);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);

        btnRead = findViewById(R.id.btnRead);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        txtResult = findViewById(R.id.txtResult);


        // READ DATA
        btnRead.setOnClickListener(v -> {

            readStudentData();

        });


        // UPDATE DATA
        btnUpdate.setOnClickListener(v -> {

            String id =
                    edtId.getText().toString().trim();

            String name =
                    edtName.getText().toString().trim();

            if (id.isEmpty() || name.isEmpty()) {

                Toast.makeText(
                        this,
                        "Enter ID and New Name",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ContentValues values =
                    new ContentValues();

            values.put("name", name);

            int result =
                    getContentResolver().update(
                            STUDENT_URI,
                            values,
                            "_id=?",
                            new String[]{id}
                    );

            if (result > 0) {

                Toast.makeText(
                        this,
                        "Student Updated Successfully",
                        Toast.LENGTH_SHORT
                ).show();

                readStudentData();

            } else {

                Toast.makeText(
                        this,
                        "Student Not Found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        // DELETE DATA
        btnDelete.setOnClickListener(v -> {

            String id =
                    edtId.getText().toString().trim();

            if (id.isEmpty()) {

                Toast.makeText(
                        this,
                        "Enter Student ID",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            int result =
                    getContentResolver().delete(
                            STUDENT_URI,
                            "_id=?",
                            new String[]{id}
                    );

            if (result > 0) {

                Toast.makeText(
                        this,
                        "Student Deleted Successfully",
                        Toast.LENGTH_SHORT
                ).show();

                edtId.setText("");
                edtName.setText("");

                readStudentData();

            } else {

                Toast.makeText(
                        this,
                        "Student Not Found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }


    // READ ALL STUDENTS
    private void readStudentData() {

        Cursor cursor =
                getContentResolver().query(
                        STUDENT_URI,
                        null,
                        null,
                        null,
                        null
                );

        if (cursor == null) {

            txtResult.setText(
                    "Unable to access data"
            );

            return;
        }

        StringBuilder data =
                new StringBuilder();

        while (cursor.moveToNext()) {

            int id =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow("_id")
                    );

            String name =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("name")
                    );

            data.append("ID: ")
                    .append(id)
                    .append("\n");

            data.append("Name: ")
                    .append(name)
                    .append("\n\n");
        }

        cursor.close();


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
}