package com.group.hamburgerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group.hamburgerapp.R;
import com.group.hamburgerapp.common.Validate;

public class ResetPasswordActivity extends AppCompatActivity {
    private  Button btn_confirm ;
    private EditText edt_password , edt_confirm_password;
    private  String password,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        init();
        initListener();
    }
    void init(){
        btn_confirm = findViewById(R.id.btn_confirm);
        edt_confirm_password=findViewById(R.id.edt_confirm_password);
        edt_password=findViewById(R.id.edt_password);
    }
    void initListener(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              handleClickConfirm();
            }
            void handleClickConfirm(){
                password=edt_password.getText().toString();
                confirmPassword=edt_confirm_password.getText().toString();
                if(Validate.CheckNull(password) || Validate.CheckNull(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),"Vui lòng điền đầy đủ các trường",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Validate.checkPassword(password)){
                        Toast.makeText(getApplicationContext(),"Vui lòng nhập mật khẩu nhiều hơn 8 ký tự",Toast.LENGTH_SHORT).show();
                    }
                    else if( Validate.checkConfirmPassword(password,confirmPassword)){
                        Toast.makeText(getApplicationContext(),"Nhập lại mật khẩu không chính xác",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Đặt lại mật khẩu thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}