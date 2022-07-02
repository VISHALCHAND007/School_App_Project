package com.example.school_app_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.school_app_project.databinding.ActivityOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import in.aabhasjindal.otptextview.OTPListener;

public class OtpActivity extends AppCompatActivity {
    ActivityOtpBinding binding;
    FirebaseAuth auth;
    String verificationId;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Instantiating auth
        auth = FirebaseAuth.getInstance();
        //getting the entered phone number

        //setting up progress dialog
        dialog = new ProgressDialog(this);
        dialog.setMessage("Sending Otp...");
        dialog.setCancelable(false);
        //starting
        dialog.show();


        String number = "";
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            number = "+91"+bundle.getString("phoneNumber");
        }
        binding.textView2.setText("Enter the otp sent to " + number);

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number)
                .setActivity(OtpActivity.this)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationId = s;
                        dialog.dismiss();
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        binding.otpView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(@NonNull String otp) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);

                auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(),AnimationActivity.class);
                            startActivity(intent);
                            finishAffinity();


                        } else {
                            Toast.makeText(OtpActivity.this, "Recheck the OTP.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}