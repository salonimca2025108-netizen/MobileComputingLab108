package com.example.practical2108;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class LocationActivity extends AppCompatActivity {

    TextView txtLatitude, txtLongitude;
    Button btnLocation;

    FusedLocationProviderClient fusedLocationClient;

    private static final int LOCATION_PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.location_activity);

        // Connect UI components
        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        btnLocation = findViewById(R.id.btnLocation);

        // Create Fused Location Client
        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this);

        // Button click
        btnLocation.setOnClickListener(v -> {

            // Check location permission
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                // Request location permission
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        },
                        LOCATION_PERMISSION_REQUEST
                );

                return;
            }

            // Get last known location
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(location -> {

                        if (location != null) {

                            // Get latitude
                            double latitude =
                                    location.getLatitude();

                            // Get longitude
                            double longitude =
                                    location.getLongitude();

                            // Display latitude
                            txtLatitude.setText(
                                    "Latitude: " + latitude
                            );

                            // Display longitude
                            txtLongitude.setText(
                                    "Longitude: " + longitude
                            );

                            // Open Google Maps
                            openGoogleMaps(
                                    latitude,
                                    longitude
                            );

                        } else {

                            Toast.makeText(
                                    LocationActivity.this,
                                    "Location not available",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    });
        });
    }

    // Method to open Google Maps
    private void openGoogleMaps(
            double latitude,
            double longitude) {

        // Create Google Maps URI
        Uri mapUri = Uri.parse(
                "geo:" + latitude + "," + longitude
                        + "?q=" + latitude + "," + longitude
                        + "(Current Location)"
        );

        // Create Intent
        Intent mapIntent =
                new Intent(Intent.ACTION_VIEW, mapUri);

        // Open specifically in Google Maps
        mapIntent.setPackage(
                "com.google.android.apps.maps"
        );

        try {

            startActivity(mapIntent);

        } catch (Exception e) {

            // Google Maps is not installed
            Toast.makeText(
                    this,
                    "Google Maps is not installed",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // Permission result
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );

        if (requestCode == LOCATION_PERMISSION_REQUEST) {

            if (grantResults.length > 0
                    && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(
                        this,
                        "Location permission granted",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Toast.makeText(
                        this,
                        "Location permission denied",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}