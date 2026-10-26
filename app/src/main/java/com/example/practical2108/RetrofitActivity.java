
package com.example.practical2108;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    Button btnGetUsers, btnCreateUser;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_layout);

        btnGetUsers = findViewById(R.id.btnGetUsers);
        btnCreateUser = findViewById(R.id.btnCreateUser);
        txtResult = findViewById(R.id.txtResult);

        btnGetUsers.setOnClickListener(v -> getUsers());

        btnCreateUser.setOnClickListener(v -> createUser());
    }

    private void getUsers() {
        txtResult.setText("Loading users...");

        ApiService api = RetrofitClient.getApiService();

        api.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(
                    Call<List<User>> call,
                    Response<List<User>> response) {

                if (response.isSuccessful()
                        && response.body() != null) {

                    StringBuilder result = new StringBuilder();

                    for (User user : response.body()) {
                        result.append("ID: ")
                                .append(user.getId())
                                .append("\nName: ")
                                .append(user.getName())
                                .append("\nEmail: ")
                                .append(user.getEmail())
                                .append("\n\n");
                    }

                    txtResult.setText(result.toString());

                } else {
                    txtResult.setText(
                            "GET failed. HTTP Code: "
                                    + response.code());
                }
            }

            @Override
            public void onFailure(
                    Call<List<User>> call,
                    Throwable t) {

                txtResult.setText(
                        "Network Error: " + t.getMessage());
            }
        });
    }


    private void createUser() {
        txtResult.setText("Sending POST request...");

        User user = new User(
                "Rahul",
                "rahul@example.com"
        );

        ApiService api = RetrofitClient.getApiService();

        api.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(
                    Call<User> call,
                    Response<User> response) {

                if (response.isSuccessful()) {
                    User createdUser = response.body();

                    String output = "POST Request Successful!\n"
                            + "HTTP Code: " + response.code();

                    if (createdUser != null) {
                        output += "\nID: " + createdUser.getId()
                                + "\nName: " + createdUser.getName()
                                + "\nEmail: " + createdUser.getEmail();
                    }

                    txtResult.setText(output);

                } else {
                    String error = "";

                    try {
                        if (response.errorBody() != null) {
                            error = response.errorBody().string();
                        }
                    } catch (Exception e) {
                        error = e.getMessage();
                    }

                    txtResult.setText(
                            "POST Failed!\n"
                                    + "HTTP Code: " + response.code()
                                    + "\nError: " + error
                    );
                }
            }

            @Override
            public void onFailure(
                    Call<User> call,
                    Throwable t) {

                txtResult.setText(
                        "Network Request Failed!\n"
                                + t.getClass().getSimpleName()
                                + "\n" + t.getMessage()
                );
            }
        });
    }

}
