package com.example.practical2108;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestApiActivity extends AppCompatActivity {

    Button btnGetData;
    Button btnPostData;
    Button btnDeleteData;

    TextView txtResult;

    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.rest_api_layout);

        // Connect UI components
        btnGetData = findViewById(R.id.btnGetData);
        btnPostData = findViewById(R.id.btnPostData);
        btnDeleteData = findViewById(R.id.btnDeleteData);

        txtResult = findViewById(R.id.txtResult);

        // Create OkHttp client
        client = new OkHttpClient();


        // =====================================================
        // GET REQUEST
        // =====================================================

        btnGetData.setOnClickListener(v -> {

            txtResult.setText("Loading GET data...");

            String url =
                    "https://jsonplaceholder.typicode.com/posts/1";

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            client.newCall(request).enqueue(new okhttp3.Callback() {

                @Override
                public void onFailure(
                        okhttp3.Call call,
                        IOException e) {

                    runOnUiThread(() ->
                            txtResult.setText(
                                    "GET Error:\n" +
                                            e.getMessage()
                            )
                    );
                }

                @Override
                public void onResponse(
                        okhttp3.Call call,
                        Response response)
                        throws IOException {

                    String result = response.body().string();

                    runOnUiThread(() ->
                            txtResult.setText(
                                    "GET RESPONSE\n\n" +
                                            result
                            )
                    );
                }
            });
        });


        // =====================================================
        // POST REQUEST
        // =====================================================

        btnPostData.setOnClickListener(v -> {

            txtResult.setText("Sending POST data...");

            String url =
                    "https://jsonplaceholder.typicode.com/posts";

            try {

                // Create JSON data
                JSONObject jsonObject = new JSONObject();

                jsonObject.put(
                        "title",
                        "My Android Post"
                );

                jsonObject.put(
                        "body",
                        "This data is sent using POST request"
                );

                jsonObject.put(
                        "userId",
                        1
                );

                // JSON Media Type
                MediaType JSON =
                        MediaType.get(
                                "application/json; charset=utf-8"
                        );

                // Create Request Body
                RequestBody body =
                        RequestBody.create(
                                jsonObject.toString(),
                                JSON
                        );

                // Create POST request
                Request request =
                        new Request.Builder()
                                .url(url)
                                .post(body)
                                .build();

                client.newCall(request).enqueue(
                        new okhttp3.Callback() {

                            @Override
                            public void onFailure(
                                    okhttp3.Call call,
                                    IOException e) {

                                runOnUiThread(() ->
                                        txtResult.setText(
                                                "POST Error:\n" +
                                                        e.getMessage()
                                        )
                                );
                            }

                            @Override
                            public void onResponse(
                                    okhttp3.Call call,
                                    Response response)
                                    throws IOException {

                                String result =
                                        response.body().string();

                                runOnUiThread(() ->
                                        txtResult.setText(
                                                "POST RESPONSE\n\n" +
                                                        result
                                        )
                                );
                            }
                        }
                );

            } catch (Exception e) {

                txtResult.setText(
                        "POST Error:\n" +
                                e.getMessage()
                );
            }
        });


        // =====================================================
        // DELETE REQUEST
        // =====================================================

        btnDeleteData.setOnClickListener(v -> {

            txtResult.setText("Deleting data...");

            String url =
                    "https://jsonplaceholder.typicode.com/posts/1";

            Request request =
                    new Request.Builder()
                            .url(url)
                            .delete()
                            .build();

            client.newCall(request).enqueue(
                    new okhttp3.Callback() {

                        @Override
                        public void onFailure(
                                okhttp3.Call call,
                                IOException e) {

                            runOnUiThread(() ->
                                    txtResult.setText(
                                            "DELETE Error:\n" +
                                                    e.getMessage()
                                    )
                            );
                        }

                        @Override
                        public void onResponse(
                                okhttp3.Call call,
                                Response response)
                                throws IOException {

                            int code = response.code();

                            runOnUiThread(() ->
                                    txtResult.setText(
                                            "DELETE RESPONSE\n\n" +
                                                    "Post deleted successfully\n\n" +
                                                    "HTTP Code: " +
                                                    code
                                    )
                            );
                        }
                    }
            );
        });
    }
}