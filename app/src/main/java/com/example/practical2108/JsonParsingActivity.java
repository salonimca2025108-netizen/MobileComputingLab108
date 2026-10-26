package com.example.practical2108;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonParsingActivity extends AppCompatActivity {

    Button btnFetchStudent;
    TextView txtJsonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_parsing_layout);

        btnFetchStudent = findViewById(R.id.btnFetchStudent);
        txtJsonResult = findViewById(R.id.txtJsonResult);

        btnFetchStudent.setOnClickListener(v -> fetchData());
    }

    private void fetchData() {
        txtJsonResult.setText("Loading student details...");

        new Thread(() -> {
            HttpURLConnection connection = null;

            try {
                URL url = new URL(
                        "https://jsonplaceholder.typicode.com/users/1"
                );

                connection =
                        (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                int statusCode = connection.getResponseCode();

                if (statusCode != HttpURLConnection.HTTP_OK) {
                    throw new Exception("HTTP Error: " + statusCode);
                }

                StringBuilder jsonData = new StringBuilder();

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()))) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        jsonData.append(line);
                    }
                }

                JSONObject obj =
                        new JSONObject(jsonData.toString());

                int id = obj.getInt("id");
                String name = obj.getString("name");
                String username = obj.getString("username");
                String email = obj.getString("email");

                String result =
                        "ID: " + id +
                                "\nName: " + name +
                                "\nUsername: " + username +
                                "\nEmail: " + email;

                runOnUiThread(() ->
                        txtJsonResult.setText(result));

            } catch (Exception e) {
                runOnUiThread(() ->
                        txtJsonResult.setText(
                                "Error: " + e.getMessage()));
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }
}
