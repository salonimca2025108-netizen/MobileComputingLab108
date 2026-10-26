
package com.example.practical2108;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionActivity extends AppCompatActivity {

    Button btnGetData, btnPostData, btnDeleteData;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.http_url_layout);

        btnGetData = findViewById(R.id.btnGetData);
        btnPostData = findViewById(R.id.btnPostData);
        btnDeleteData = findViewById(R.id.btnDeleteData);
        txtResult = findViewById(R.id.txtResult);

        // GET request
        btnGetData.setOnClickListener(v -> {
            sendRequest("GET");
        });

        // POST request
        btnPostData.setOnClickListener(v -> {
            sendRequest("POST");
        });

        // DELETE request
        btnDeleteData.setOnClickListener(v -> {
            sendRequest("DELETE");
        });
    }

    private void sendRequest(String method) {

        txtResult.setText(method + " request in progress...");

        new Thread(() -> {

            HttpURLConnection connection = null;

            try {
                String urlString;

                if (method.equals("POST")) {
                    urlString =
                            "https://jsonplaceholder.typicode.com/posts";
                } else {
                    urlString =
                            "https://jsonplaceholder.typicode.com/posts/1";
                }

                // Step 1: Create URL
                URL url = new URL(urlString);

                // Step 2: Open connection
                connection =
                        (HttpURLConnection) url.openConnection();

                // Step 3: Set request method
                connection.setRequestMethod(method);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                // POST request
                if (method.equals("POST")) {

                    connection.setDoOutput(true);
                    connection.setRequestProperty(
                            "Content-Type",
                            "application/json; charset=UTF-8"
                    );

                    JSONObject json = new JSONObject();
                    json.put("title", "My Android Post");
                    json.put("body", "POST using HttpURLConnection");
                    json.put("userId", 1);

                    byte[] data = json.toString()
                            .getBytes("UTF-8");

                    try (OutputStream output =
                                 connection.getOutputStream()) {
                        output.write(data);
                    }
                }

                // Step 4: Get HTTP response code
                int responseCode = connection.getResponseCode();

                // Step 5: Read successful or error response
                InputStream stream;

                if (responseCode >= 200 && responseCode < 400) {
                    stream = connection.getInputStream();
                } else {
                    stream = connection.getErrorStream();
                }

                StringBuilder result = new StringBuilder();

                if (stream != null) {
                    try (BufferedReader reader =
                                 new BufferedReader(
                                         new InputStreamReader(stream))) {

                        String line;

                        while ((line = reader.readLine()) != null) {
                            result.append(line).append("\n");
                        }
                    }
                }

                String message;

                if (responseCode >= 200 && responseCode < 300) {
                    message = method + " SUCCESS\n\n"
                            + "HTTP Code: " + responseCode
                            + "\n\n";

                    if (method.equals("DELETE")) {
                        message += "Delete request successful.";
                    } else {
                        message += result.toString();
                    }

                } else {
                    message = method + " FAILED\n\n"
                            + "HTTP Code: " + responseCode
                            + "\n\n" + result;
                }

                // Step 6: Display response
                final String finalMessage = message;
                runOnUiThread(() -> txtResult.setText(finalMessage));

            } catch (Exception e) {

                runOnUiThread(() ->
                        txtResult.setText(
                                "Error: " + e.getMessage()
                        )
                );

            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
            }

        }).start();
    }
}
