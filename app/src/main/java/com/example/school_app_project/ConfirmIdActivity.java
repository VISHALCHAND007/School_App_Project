package com.example.school_app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.school_app_project.databinding.ActivityConfirmIdBinding;

public class ConfirmIdActivity extends AppCompatActivity {
    Button accept_button;
    ActivityConfirmIdBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        accept_button = findViewById(R.id.accept_button);

        accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the value of phone number from MainActivity
                String phoneNumber = "";
                Bundle bundle = getIntent().getExtras();
                if(bundle != null) {
                    phoneNumber = bundle.getString("phoneNumber");
                }
                Intent intent = new Intent(getApplicationContext(),OtpActivity.class);
                intent.putExtra("phoneNumber",phoneNumber);
                startActivity(intent);
            }
        });
    }
}