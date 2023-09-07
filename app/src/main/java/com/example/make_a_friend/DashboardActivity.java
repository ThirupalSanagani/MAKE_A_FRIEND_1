package com.example.make_a_friend; // Change this package name to match your project package

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    private ImageView videoCallImageView, notificationImageView, voiceCallImageView, moreImageView, calendarImageView, mapsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        // Initialize views
        videoCallImageView = findViewById(R.id.imageVideocall);
        notificationImageView = findViewById(R.id.imageNotification);
        voiceCallImageView = findViewById(R.id.imageVoicecall);
        moreImageView = findViewById(R.id.imageView9);
        calendarImageView = findViewById(R.id.imageView10); // Assuming the ID for the calendar button is imageView10, update it accordingly.
        mapsImageView = findViewById(R.id.imageView2); // Assuming the ID for the maps button is imageViewMaps, update it accordingly.
        ImageView logoutButton = findViewById(R.id.logout_btn);

        builder = new AlertDialog.Builder(this);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("ALERT!!")
                        .setMessage("DO YO WANT TO LOGOUT")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                            }
                        })
                        .show();
            }
        });

        // Set click listeners for the image views
        videoCallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Video Call button clicked!");
            }
        });

        notificationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Notification button clicked!");
            }
        });

        voiceCallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Voice Call button clicked!");
            }
        });

        moreImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMoreIconActivity();
            }
        });

        calendarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCalendarActivity();
            }
        });

        mapsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMapsActivity();
            }
        });
    }

    private void navigateToMoreIconActivity() {
        Intent intent = new Intent(this, MoreIconActivity.class);
        startActivity(intent);
    }

    private void navigateToCalendarActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    private void navigateToMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    // Helper method to show a toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
