package com.group.hamburgerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.hamburgerapp.R;
import com.group.hamburgerapp.common.Validate;
import com.group.hamburgerapp.database.UserDatabase;
import com.group.hamburgerapp.entity.User;
import com.group.hamburgerapp.ultil.FuncHelper;

public class RegisterActivity extends AppCompatActivity {
    private  Button btn_register;
    private  EditText edt_name,edt_email,edt_address,edt_phone,edt_password;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        initListener();
    }
    void init(){
        btn_register=findViewById(R.id.btn_register);
        edt_email=findViewById(R.id.edt_email);
        edt_name=findViewById(R.id.edt_name);
        edt_address=findViewById(R.id.edt_address);
        edt_password=findViewById(R.id.edt_password);
        edt_phone=findViewById(R.id.edt_phone);
        progressBar=findViewById(R.id.progress_bar);
        mAuth = FirebaseAuth.getInstance();
    }
    void initListener(){
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickRegister();
            }
        });
    }
    void handleOnClickRegister(){
        boolean name= FuncHelper.validateName(edt_name.getText().toString(),getApplicationContext());
        boolean email =FuncHelper.validateEmail(edt_email.getText().toString(),getApplicationContext());
        boolean phone = FuncHelper.validatePhone(edt_phone.getText().toString(),getApplicationContext());
        boolean address=  FuncHelper.validateAddress(edt_address.getText().toString(),getApplicationContext());
        boolean password=  FuncHelper.validatePassword(edt_password.getText().toString(),getApplicationContext());
        if(name&&email&&phone&&address&&password){
            handleCreateUser(edt_email.getText().toString(),edt_password.getText().toString());
            progressBar.setVisibility(View.VISIBLE);
        }
    }
    void handleCreateUser(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TEST", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            progressBar.setVisibility(View.INVISIBLE);
                            UserDatabase.writeNewUser( user.getUid().toString(),edt_name.getText().toString(),edt_email.getText().toString(),edt_phone.getText().toString(),edt_address.getText().toString(),edt_password.getText().toString());
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.w("TEST", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}