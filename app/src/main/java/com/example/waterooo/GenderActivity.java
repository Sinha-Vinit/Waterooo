package com.example.waterooo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class GenderActivity extends AppCompatActivity {

    Spinner genderSpinner;
    Button nextButton;

    String userName;
    String userEmail;
    String userPassword;
    int userAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        genderSpinner = findViewById(R.id.genderSpinner);
        nextButton = findViewById(R.id.nextButton);

        userName = getIntent().getStringExtra("USER_NAME");
        userEmail = getIntent().getStringExtra("USER_EMAIL");
        userPassword = getIntent().getStringExtra("USER_PASSWORD");
        userAge = getIntent().getIntExtra("USER_AGE", 21);

        String[] genders = {
                "Male",
                "Female",
                "Prefer not to say"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                genders
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        genderSpinner.setAdapter(adapter);

        nextButton.setOnClickListener(v -> {

            String selectedGender =
                    genderSpinner.getSelectedItem().toString();

            Intent intent = new Intent(
                    GenderActivity.this,
                    HeightActivity.class
            );

            intent.putExtra("USER_NAME", userName);
            intent.putExtra("USER_EMAIL", userEmail);
            intent.putExtra("USER_PASSWORD", userPassword);
            intent.putExtra("USER_AGE", userAge);
            intent.putExtra("USER_GENDER", selectedGender);

            startActivity(intent);
        });
    }
}