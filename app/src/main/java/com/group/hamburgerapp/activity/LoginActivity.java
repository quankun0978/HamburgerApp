package com.group.hamburgerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group.hamburgerapp.R;

public class LoginActivity extends AppCompatActivity {
    private TextView txt_forgot_password;
    private Button btn_continue_google;
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
    }
    void initListener(){
        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickForgotPassword();
            }
        });
    }
    void handleOnClickForgotPassword(){
        Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }

}