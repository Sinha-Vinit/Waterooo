package com.example.waterooo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AgeActivity extends AppCompatActivity {

    SeekBar ageSeekBar;
    TextView ageValue;
    Button nextButton;

    String userName;
    String userEmail;
    String userPassword;

    int selectedAge = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        ageSeekBar = findViewById(R.id.ageSeekBar);
        ageValue = findViewById(R.id.ageValue);
        nextButton = findViewById(R.id.nextButton);

        userName = getIntent().getStringExtra("USER_NAME");
        userEmail = getIntent().getStringExtra("USER_EMAIL");
        userPassword = getIntent().getStringExtra("USER_PASSWORD");

        ageSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        selectedAge = progress + 7;
                        ageValue.setText(String.valueOf(selectedAge));
                    }

                    @Override
                    public void onStartTrackingTouch(
                            SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(
                            SeekBar seekBar) {
                    }
                });

        nextButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AgeActivity.this,
                    GenderActivity.class
            );

            intent.putExtra("USER_NAME", userName);
            intent.putExtra("USER_EMAIL", userEmail);
            intent.putExtra("USER_PASSWORD", userPassword);
            intent.putExtra("USER_AGE", selectedAge);

            startActivity(intent);
        });
    }
}