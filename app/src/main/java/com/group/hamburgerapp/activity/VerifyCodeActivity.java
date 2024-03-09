package com.group.hamburgerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.group.hamburgerapp.R;

public class VerifyCodeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btn_confirm,btn_resend_otp;
    private TextView txt_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        init();
        initListener();
    }
    void init(){
        btn_confirm=findViewById(R.id.btn_confirm);
        btn_resend_otp=findViewById(R.id.btn_resend_otp);
        txt_otp=findViewById(R.id.edt_otp);
        mAuth= FirebaseAuth.getInstance();
    }
    void initListener(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = getIntent().getStringExtra("code");
                verifyPhoneNumberWithCode(code,txt_otp.getText().toString().trim());
            }
        });
    }
    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
        // [END verify_with_code]
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                             Toast.makeText(getApplicationContext(),"Thành công",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(VerifyCodeActivity.this,ResetPasswordActivity.class);
                            startActivity(intent);
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(getApplicationContext(),"Mã OTP không chính xác",Toast.LENGTH_SHORT).show();

//                            Log.w("CHECK ERROR", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                Toast.makeText(getApplicationContext(), "The verification code entered was invalid",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}