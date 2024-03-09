package com.group.hamburgerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.hamburgerapp.R;

public class LoginActivity extends AppCompatActivity {
    private TextView txt_forgot_password,txt_register;
    private EditText edt_email,edt_password;
    private ProgressBar progressBar;
    private Button btn_continue_google ,btn_login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initListener();
    }
    void init(){
        txt_forgot_password=findViewById(R.id.txt_forgot_password);
        btn_continue_google=findViewById(R.id.btn_continue_google);
        btn_login=findViewById(R.id.btn_login);
        txt_register=findViewById(R.id.txt_register);
        edt_email=findViewById(R.id.edt_email);
        edt_password=findViewById(R.id.edt_password);
        progressBar=findViewById(R.id.progress_bar);
        mAuth = FirebaseAuth.getInstance();
    }
    void initListener(){
        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickForgotPassword();
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickRegister();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickLogin(edt_email.getText().toString(),edt_password.getText().toString());
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    void handleOnClickLogin(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("user",user.getEmail().toString());
                            startActivity(intent);
                            finishAffinity();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Email hoặc password không chính xác",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    void handleOnClickForgotPassword(){
        Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }
    void handleOnClickRegister(){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

}