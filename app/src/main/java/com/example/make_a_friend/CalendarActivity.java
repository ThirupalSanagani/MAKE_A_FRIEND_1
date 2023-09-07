package com.example.make_a_friend; // Change this package name to match your project package

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_icon_dashb);

        // Initialize the CalendarView
        CalendarView calendarView = findViewById(R.id.calendarView);

        // Set a date change listener for the CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Show a Toast with the selected date
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                showToast("Selected Date: " + selectedDate);
            }
        });
    }

    // Helper method to show a toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
