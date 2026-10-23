package com.example.waterooo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeText;
    TextView goalText;
    TextView consumedText;
    TextView remainingText;
    ProgressBar waterProgress;

    Button btn250;
    Button btn500;
    Button btn750;

    Button profileButton;
    Button settingsButton;
    Button addCustomButton;

    EditText customWaterInput;

    int waterGoal;
    int waterConsumed = 0;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeText = findViewById(R.id.welcomeText);
        goalText = findViewById(R.id.goalText);
        consumedText = findViewById(R.id.consumedText);
        remainingText = findViewById(R.id.remainingText);
        waterProgress = findViewById(R.id.waterProgress);

        btn250 = findViewById(R.id.btn250);
        btn500 = findViewById(R.id.btn500);
        btn750 = findViewById(R.id.btn750);
        addCustomButton = findViewById(R.id.addCustomButton);

        customWaterInput = findViewById(R.id.customWaterInput);
        profileButton = findViewById(R.id.profileButton);

        profileButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HomeActivity.this,
                    ProfileActivity.class
            );

            intent.putExtra("USER_NAME", userName);
            intent.putExtra("USER_EMAIL",
                    getIntent().getStringExtra("USER_EMAIL"));
            intent.putExtra("USER_AGE",
                    getIntent().getIntExtra("USER_AGE", 18));
            intent.putExtra("USER_GENDER",
                    getIntent().getStringExtra("USER_GENDER"));
            intent.putExtra("USER_HEIGHT",
                    getIntent().getIntExtra("USER_HEIGHT", 170));
            intent.putExtra("USER_WEIGHT",
                    getIntent().getFloatExtra("USER_WEIGHT", 60));
            intent.putExtra("WATER_GOAL", waterGoal);

            startActivity(intent);
        });

        settingsButton = findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HomeActivity.this,
                    SettingsActivity.class
            );

            startActivity(intent);
        });

        userName = getIntent().getStringExtra("USER_NAME");
        waterGoal = getIntent().getIntExtra("WATER_GOAL", 2000);

        welcomeText.setText("Hello, " + userName + "! 👋");

        goalText.setText(
                "Daily Goal: " + waterGoal + " ml"
        );

        updateWaterProgress();

        btn250.setOnClickListener(v -> addWater(250));

        btn500.setOnClickListener(v -> addWater(500));

        btn750.setOnClickListener(v -> addWater(750));

        addCustomButton.setOnClickListener(v -> {

            String amountText =
                    customWaterInput.getText().toString().trim();

            if (amountText.isEmpty()) {
                customWaterInput.setError(
                        "Enter water amount"
                );
                return;
            }

            int amount;

            try {
                amount = Integer.parseInt(amountText);
            } catch (NumberFormatException e) {
                customWaterInput.setError(
                        "Enter a valid amount"
                );
                return;
            }

            if (amount <= 0) {
                customWaterInput.setError(
                        "Amount must be greater than 0"
                );
                return;
            }

            addWater(amount);

            customWaterInput.setText("");


        });
    }

    private void addWater(int amount) {

        waterConsumed += amount;

        updateWaterProgress();

        Toast.makeText(
                HomeActivity.this,
                amount + " ml added 💧",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void updateWaterProgress() {

        consumedText.setText(
                waterConsumed + " ml"
        );

        int remaining = waterGoal - waterConsumed;

        if (remaining < 0) {
            remaining = 0;
        }

        remainingText.setText(
                remaining + " ml remaining"
        );

        int progress =
                (waterConsumed * 100) / waterGoal;

        if (progress > 100) {
            progress = 100;
        }

        waterProgress.setProgress(progress);
    }
}