package com.example.hey.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hey.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class OTPVerifyActivity extends AppCompatActivity {

    View otp;
    EditText n1,n2,n3,n4,n5,n6;
    TextView mobileNumber;
    String sentOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_verify);

        otp = findViewById(R.id.otp);

        Button verifyOtp = findViewById(R.id.buttonverifyotp);

        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
        n4 = findViewById(R.id.n4);
        n5 = findViewById(R.id.n5);
        n6 = findViewById(R.id.n6);

        mobileNumber = findViewById(R.id.mobileNumber);
        mobileNumber.setText("+91-" + getIntent().getStringExtra("mobileNumber").toString());

        sentOtp = getIntent().getStringExtra("sentOtp");

        final ProgressBar receiving = findViewById(R.id.progressbar_verify_otp);

        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!n1.getText().toString().trim().isEmpty() &&
                    !n2.getText().toString().trim().isEmpty() &&
                    !n3.getText().toString().trim().isEmpty() &&
                    !n4.getText().toString().trim().isEmpty() &&
                    !n5.getText().toString().trim().isEmpty() &&
                    !n6.getText().toString().trim().isEmpty()) {

                    String enterOtp =   n1.getText().toString() +
                                        n2.getText().toString() +
                                        n3.getText().toString() +
                                        n4.getText().toString() +
                                        n5.getText().toString() +
                                        n6.getText().toString() ;

                    if (sentOtp != null) {
                        receiving.setVisibility(View.VISIBLE);
                        verifyOtp.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sentOtp ,enterOtp);

                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                receiving.setVisibility(View.GONE);
                                verifyOtp.setVisibility(View.VISIBLE);

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), ProfileSetup.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                                else {
                                     Toast.makeText(OTPVerifyActivity.this ,"Enter correct OTP" ,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                    else {
                        Toast.makeText(OTPVerifyActivity.this ,"Please check internet connection" ,Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(OTPVerifyActivity.this ,"Verifying OTP..." ,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(OTPVerifyActivity.this ,"Please enter OTP correctly" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        CombineOtp();

        findViewById(R.id.resendOtp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobileNumber").toString(),
                        60,
                        TimeUnit.SECONDS,
                        OTPVerifyActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(OTPVerifyActivity.this ,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newSentOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                sentOtp = newSentOtp;
                                Toast.makeText(OTPVerifyActivity.this ,"OTP Sent successfully" ,Toast.LENGTH_SHORT).show();

                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED ,0);
                                otp.requestFocus();

                            }
                        }
                );

            }
        });
    }

    private void CombineOtp() {

        n1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    n2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    n3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    n4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    n5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    n6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}