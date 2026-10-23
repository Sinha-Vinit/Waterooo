package com.example.waterooo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WeightActivity extends AppCompatActivity {

    EditText etweightInput;
    Button btnSave;

    String userName;
    String userEmail;
    String userPassword;
    String userGender;

    int userAge;
    int userHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        etweightInput = findViewById(R.id.etweightInput);
        btnSave = findViewById(R.id.btnSave);

        userName = getIntent().getStringExtra("USER_NAME");
        userEmail = getIntent().getStringExtra("USER_EMAIL");
        userPassword = getIntent().getStringExtra("USER_PASSWORD");
        userAge = getIntent().getIntExtra("USER_AGE", 21);
        userGender = getIntent().getStringExtra("USER_GENDER");
        userHeight = getIntent().getIntExtra("USER_HEIGHT", 170);

        btnSave.setOnClickListener(v -> saveUserInfo());
    }

    private void saveUserInfo() {

        String weightText =
                etweightInput.getText().toString().trim();

        if (weightText.isEmpty()) {
            etweightInput.setError("Enter your weight");
            etweightInput.requestFocus();
            return;
        }

        float weight;

        try {
            weight = Float.parseFloat(weightText);
        } catch (NumberFormatException e) {
            etweightInput.setError("Enter a valid weight");
            etweightInput.requestFocus();
            return;
        }

        if (weight < 20 || weight > 300) {
            etweightInput.setError(
                    "Weight should be between 20 and 300 kg"
            );
            etweightInput.requestFocus();
            return;
        }

        // Basic daily water goal calculation
        int waterGoal = Math.round(weight * 35);

        Toast.makeText(
                WeightActivity.this,
                "Your daily goal is " + waterGoal + " ml 💧",
                Toast.LENGTH_LONG
        ).show();

        Intent intent = new Intent(
                WeightActivity.this,
                HomeActivity.class
        );

        intent.putExtra("USER_NAME", userName);
        intent.putExtra("USER_EMAIL", userEmail);
        intent.putExtra("USER_PASSWORD", userPassword);
        intent.putExtra("USER_AGE", userAge);
        intent.putExtra("USER_GENDER", userGender);
        intent.putExtra("USER_HEIGHT", userHeight);
        intent.putExtra("USER_WEIGHT", weight);
        intent.putExtra("WATER_GOAL", waterGoal);

        startActivity(intent);
        finish();
    }
}