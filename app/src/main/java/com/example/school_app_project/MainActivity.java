package com.example.school_app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.school_app_project.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button next;
    ActivityMainBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        //checking if user has already logged in or not
        if(auth.getCurrentUser() != null) {
            Intent intent = new Intent(this,AfterLoginActivity.class);
            startActivity(intent);
            finish();
        }

        next = findViewById(R.id.accept_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.phoneNumber.getText().toString().equals("")) {
                    binding.phoneNumber.setError("Enter a Phone Number.");
                } else {
                    Intent intent = new Intent(MainActivity.this, ConfirmIdActivity.class);
                    intent.putExtra("phoneNumber",binding.phoneNumber.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}