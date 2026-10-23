package com.example.waterooo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText nameInput;
    EditText emailInput;
    EditText passwordInput;
    EditText confirmPasswordInput;

    Button registerButton;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.registerEmailInput);
        passwordInput = findViewById(R.id.registerPasswordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);

        registerButton = findViewById(R.id.registerButton);
        loginText = findViewById(R.id.loginText);

        registerButton.setOnClickListener(v -> registerUser());

        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(
                    RegisterActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
            finish();
        });
    }

    private void registerUser() {

        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword =
                confirmPasswordInput.getText().toString().trim();

        if (name.isEmpty()) {
            nameInput.setError("Enter your name");
            nameInput.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailInput.setError("Enter your email");
            emailInput.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Enter a valid email");
            emailInput.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordInput.setError("Enter a password");
            passwordInput.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordInput.setError(
                    "Password must be at least 6 characters"
            );
            passwordInput.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordInput.setError("Confirm your password");
            confirmPasswordInput.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordInput.setError(
                    "Passwords do not match"
            );
            confirmPasswordInput.requestFocus();
            return;
        }

        Toast.makeText(
                RegisterActivity.this,
                "Registration successful!",
                Toast.LENGTH_SHORT
        ).show();

        Intent intent = new Intent(
                RegisterActivity.this,
                AgeActivity.class
        );

        intent.putExtra("USER_NAME", name);
        intent.putExtra("USER_EMAIL", email);
        intent.putExtra("USER_PASSWORD", password);

        startActivity(intent);
        finish();
    }
}