package com.group.hamburgerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.group.hamburgerapp.R;

import java.util.concurrent.TimeUnit;

public class ForgotPasswordActivity extends AppCompatActivity {
    private Button btn_confirm ;
    private FirebaseAuth mAuth;
    private EditText edt_phone ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        FirebaseApp.initializeApp(getApplicationContext()); // Thêm dòng này để khởi tạo Firebase
        mAuth = FirebaseAuth.getInstance();
        init();
        initListener();
    }
    void init(){
        btn_confirm=findViewById(R.id.btn_confirm);
        edt_phone=findViewById(R.id.edt_phone);
    }
    void initListener(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickConfirm();
            }
        });
    }
    private void handleOnClickConfirm() {
        Log.d("TEST" ,"hihi");
        handleSendOtp(edt_phone.getText().toString().trim());
    }
    private void handleSendOtp(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Log.e("TEST" ,"send success");
                            }
                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Log.e("TEST" ,"send fail");
                            }
                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                // The SMS verification code has been sent to the provided phone number, we
                                // now need to ask the user to enter the code and then construct a credential
                                // by combining the code with a verification ID.
                                Log.e("CODE", "onCodeSent:" + verificationId);
                                Intent intent = new Intent(ForgotPasswordActivity.this,VerifyCodeActivity.class);
                                intent.putExtra("code",verificationId);
                                startActivity(intent);
                                // Save verification ID and resending token so we can use them later
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}