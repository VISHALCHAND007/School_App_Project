package com.example.school_app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.school_app_project.databinding.ActivityAnimationBinding;

public class AnimationActivity extends AppCompatActivity {
    ActivityAnimationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnimationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.animationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AnimationActivity.this,AfterLoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        },4000);
    }
}