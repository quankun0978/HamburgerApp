package com.group.hamburgerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.hamburgerapp.R;
import com.group.hamburgerapp.database.UserDatabase;

public class LogoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(UserDatabase.checkLogin()){
                    Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }
                else {
                    Intent intent = new Intent(LogoActivity.this, IntroduceActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }
            }
        }, 3000);
    }
}