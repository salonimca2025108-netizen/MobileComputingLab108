package com.example.practical2108;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class NotificationActivity extends AppCompatActivity {

    Button btnOrder;

    String channelId = "order_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java file with notification_layout.xml
        setContentView(R.layout.notification_layout);

        // Find button
        btnOrder = findViewById(R.id.btnOrder);

        // Create notification channel
        createNotificationChannel();

        // Request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        101
                );
            }
        }

        // Place Order button click
        btnOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Check notification permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    if (checkSelfPermission(
                            Manifest.permission.POST_NOTIFICATIONS)
                            != PackageManager.PERMISSION_GRANTED) {

                        requestPermissions(
                                new String[]{Manifest.permission.POST_NOTIFICATIONS},
                                101
                        );

                        return;
                    }
                }

                // Create notification
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(
                                NotificationActivity.this,
                                channelId
                        );

                // Notification icon
                builder.setSmallIcon(
                        android.R.drawable.ic_dialog_info
                );

                // Notification title
                builder.setContentTitle(
                        "Order Confirmed"
                );

                // Notification message
                builder.setContentText(
                        "Your pizza order has been placed successfully."
                );

                // Notification priority
                builder.setPriority(
                        NotificationCompat.PRIORITY_DEFAULT
                );

                // Remove notification when clicked
                builder.setAutoCancel(true);

                // Get NotificationManager
                NotificationManager manager =
                        (NotificationManager)
                                getSystemService(
                                        NOTIFICATION_SERVICE
                                );

                // Show notification
                manager.notify(
                        1,
                        builder.build()
                );
            }
        });
    }

    // Create Notification Channel
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            channelId,
                            "Order Notifications",
                            NotificationManager.IMPORTANCE_DEFAULT
                    );

            NotificationManager manager =
                    getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);
        }
    }
}