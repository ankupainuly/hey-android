package com.example.hey.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hey.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {

    EditText mobileNumber;
    Button getOtp;
    ProgressBar sending;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(OTPActivity.this ,ChatActivity.class);
            startActivity(intent);
            finish();
        }

        mobileNumber = findViewById(R.id.input_mobile_number);
        getOtp = findViewById(R.id.setupButton);
        sending = findViewById(R.id.progressbar_sending_otp);

        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mobileNumber.getText().toString().trim().isEmpty()){
                    if (mobileNumber.getText().toString().trim().length() == 10){

                        sending.setVisibility(View.VISIBLE);
                        getOtp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + mobileNumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                OTPActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        sending.setVisibility(View.GONE);
                                        getOtp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        sending.setVisibility(View.GONE);
                                        getOtp.setVisibility(View.VISIBLE);
                                        Toast.makeText(OTPActivity.this ,e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String sentOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        sending.setVisibility(View.GONE);
                                        getOtp.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(getApplicationContext() ,OTPVerifyActivity.class);
                                        intent.putExtra("mobileNumber" ,mobileNumber.getText().toString());
                                        intent.putExtra("sentOtp", sentOtp);
                                        startActivity(intent);
                                    }
                                }
                        );

                    }
                    else {
                        Toast.makeText(OTPActivity.this ,"Please enter mobile number correctly" ,Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(OTPActivity.this ,"Enter mobile number" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}