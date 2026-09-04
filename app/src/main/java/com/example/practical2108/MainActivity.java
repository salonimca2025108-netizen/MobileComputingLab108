package com.example.practical2108;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnProvider, btnConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnProvider = findViewById(R.id.btnProvider);
        btnConsumer = findViewById(R.id.btnConsumer);

        // Open Provider Module
        btnProvider.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ProviderActivity.class
            );

            startActivity(intent);
        });

        // Open Consumer Module
        btnConsumer.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ConsumerActivity.class
            );

            startActivity(intent);
        });
    }
}