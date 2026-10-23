package com.example.waterooo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView nameText;
    TextView emailText;
    TextView ageText;
    TextView genderText;
    TextView heightText;
    TextView weightText;
    TextView goalText;

    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        ageText = findViewById(R.id.ageText);
        genderText = findViewById(R.id.genderText);
        heightText = findViewById(R.id.heightText);
        weightText = findViewById(R.id.weightText);
        goalText = findViewById(R.id.goalText);

        updateButton = findViewById(R.id.updateButton);

        String name = getIntent().getStringExtra("USER_NAME");
        String email = getIntent().getStringExtra("USER_EMAIL");
        int age = getIntent().getIntExtra("USER_AGE", 21);
        String gender = getIntent().getStringExtra("USER_GENDER");
        int height = getIntent().getIntExtra("USER_HEIGHT", 170);
        float weight = getIntent().getFloatExtra("USER_WEIGHT", 60);
        int goal = getIntent().getIntExtra("WATER_GOAL", 2000);

        nameText.setText(name);
        emailText.setText(email);
        ageText.setText(age + " years");
        genderText.setText(gender);
        heightText.setText(height + " cm");
        weightText.setText(weight + " kg");
        goalText.setText(goal + " ml/day");

        updateButton.setOnClickListener(v -> {

            // We will implement profile editing later.
            // For now, show a message.

            android.widget.Toast.makeText(
                    ProfileActivity.this,
                    "Profile editing coming next!",
                    android.widget.Toast.LENGTH_SHORT
            ).show();
        });
    }
}