package com.example.waterooo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HeightActivity extends AppCompatActivity {

    EditText heightInput;
    Button nextButton;

    String userName;
    String userEmail;
    String userPassword;
    String userGender;
    int userAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        heightInput = findViewById(R.id.heightInput);
        nextButton = findViewById(R.id.nextButton);

        userName = getIntent().getStringExtra("USER_NAME");
        userEmail = getIntent().getStringExtra("USER_EMAIL");
        userPassword = getIntent().getStringExtra("USER_PASSWORD");
        userAge = getIntent().getIntExtra("USER_AGE", 21);
        userGender = getIntent().getStringExtra("USER_GENDER");

        nextButton.setOnClickListener(v -> {

            String heightText =
                    heightInput.getText().toString().trim();

            if (heightText.isEmpty()) {
                heightInput.setError("Enter your height");
                heightInput.requestFocus();
                return;
            }

            int height;

            try {
                height = Integer.parseInt(heightText);
            } catch (NumberFormatException e) {
                heightInput.setError("Enter a valid height");
                heightInput.requestFocus();
                return;
            }

            if (height < 50 || height > 250) {
                heightInput.setError(
                        "Height should be between 50 and 250 cm"
                );
                heightInput.requestFocus();
                return;
            }

            Intent intent = new Intent(
                    HeightActivity.this,
                    WeightActivity.class
            );

            intent.putExtra("USER_NAME", userName);
            intent.putExtra("USER_EMAIL", userEmail);
            intent.putExtra("USER_PASSWORD", userPassword);
            intent.putExtra("USER_AGE", userAge);
            intent.putExtra("USER_GENDER", userGender);
            intent.putExtra("USER_HEIGHT", height);

            startActivity(intent);
        });
    }
}