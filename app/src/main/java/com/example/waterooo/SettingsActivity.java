package com.example.waterooo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch reminderSwitch;
    Switch notificationSwitch;
    Switch darkModeSwitch;

    Button reminderSettingsButton;

    Button unitMlButton;
    Button unitLiterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        reminderSwitch = findViewById(R.id.reminderSwitch);
        notificationSwitch = findViewById(R.id.notificationSwitch);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        unitMlButton = findViewById(R.id.unitMlButton);
        unitLiterButton = findViewById(R.id.unitLiterButton);
        reminderSettingsButton =
                findViewById(R.id.reminderSettingsButton);

        reminderSettingsButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    ReminderActivity.class
            );

            startActivity(intent);
        });

        reminderSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    if (isChecked) {
                        Toast.makeText(
                                SettingsActivity.this,
                                "Reminders enabled 🔔",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(
                                SettingsActivity.this,
                                "Reminders disabled",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        notificationSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    if (isChecked) {
                        Toast.makeText(
                                SettingsActivity.this,
                                "Notifications enabled 🔔",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(
                                SettingsActivity.this,
                                "Notifications disabled",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        darkModeSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    if (isChecked) {
                        Toast.makeText(
                                SettingsActivity.this,
                                "Dark mode selected 🌙",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(
                                SettingsActivity.this,
                                "Light mode selected ☀️",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        unitMlButton.setOnClickListener(v -> {

            Toast.makeText(
                    SettingsActivity.this,
                    "Water unit: ml",
                    Toast.LENGTH_SHORT
            ).show();
        });

        unitLiterButton.setOnClickListener(v -> {

            Toast.makeText(
                    SettingsActivity.this,
                    "Water unit: Litres",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}