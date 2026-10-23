package com.example.waterooo;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ReminderActivity extends AppCompatActivity {

    Switch reminderSwitch;
    Spinner intervalSpinner;
    Button saveButton;

    private static final int NOTIFICATION_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        reminderSwitch = findViewById(R.id.reminderSwitch);
        intervalSpinner = findViewById(R.id.intervalSpinner);
        saveButton = findViewById(R.id.saveButton);

        String[] intervals = {
                "30 minutes",
                "1 hour",
                "2 hours",
                "3 hours",
                "4 hours"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                intervals
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        intervalSpinner.setAdapter(adapter);

        requestNotificationPermission();

        saveButton.setOnClickListener(v -> {

            if (!reminderSwitch.isChecked()) {

                cancelReminder();

                Toast.makeText(
                        ReminderActivity.this,
                        "Reminders disabled",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            String selectedInterval =
                    intervalSpinner.getSelectedItem().toString();

            long intervalMillis =
                    getIntervalMillis(selectedInterval);

            if (intervalMillis == 0) {
                Toast.makeText(
                        ReminderActivity.this,
                        "Please select a valid interval",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            scheduleReminder(intervalMillis);

            Toast.makeText(
                    ReminderActivity.this,
                    "Reminder set every "
                            + selectedInterval + " 🔔",
                    Toast.LENGTH_LONG
            ).show();
        });
    }

    private long getIntervalMillis(String interval) {

        switch (interval) {

            case "30 minutes":
               // return 30 * 60 * 1000L;
                return 10 * 1000L;

            case "1 hour":
                return 60 * 60 * 1000L;

            case "2 hours":
                return 2 * 60 * 60 * 1000L;

            case "3 hours":
                return 3 * 60 * 60 * 1000L;

            case "4 hours":
                return 4 * 60 * 60 * 1000L;

            default:
                return 0;
        }
    }

    private void scheduleReminder(long intervalMillis) {

        AlarmManager alarmManager =
                (AlarmManager) getSystemService(
                        Context.ALARM_SERVICE
                );

        Intent intent =
                new Intent(
                        ReminderActivity.this,
                        ReminderReceiver.class
                );

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(
                        ReminderActivity.this,
                        100,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                                | PendingIntent.FLAG_IMMUTABLE
                );

        long firstTriggerTime =
                System.currentTimeMillis() + intervalMillis;

        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                firstTriggerTime,
                intervalMillis,
                pendingIntent
        );
    }

    private void cancelReminder() {

        AlarmManager alarmManager =
                (AlarmManager) getSystemService(
                        Context.ALARM_SERVICE
                );

        Intent intent =
                new Intent(
                        ReminderActivity.this,
                        ReminderReceiver.class
                );

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(
                        ReminderActivity.this,
                        100,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                                | PendingIntent.FLAG_IMMUTABLE
                );

        alarmManager.cancel(pendingIntent);
    }

    private void requestNotificationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.POST_NOTIFICATIONS
                        },
                        NOTIFICATION_PERMISSION_CODE
                );
            }
        }
    }
}