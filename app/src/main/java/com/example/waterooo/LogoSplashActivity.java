package com.example.waterooo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LogoSplashActivity extends AppCompatActivity {
    private ImageView ivlogo;

    private static final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_splash);

        ImageView logo = findViewById(R.id.ivlogo);

        Animation animation = AnimationUtils.loadAnimation(
                this,
                R.anim.logo_animation
        );

        logo.startAnimation(animation);

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(
                    LogoSplashActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
            finish();

        }, SPLASH_TIME);
    }
}