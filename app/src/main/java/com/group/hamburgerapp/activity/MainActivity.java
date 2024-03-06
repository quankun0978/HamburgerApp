package com.group.hamburgerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group.hamburgerapp.R;
import com.group.hamburgerapp.database.HamburgerDatabase;
import com.group.hamburgerapp.entity.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_save,btn_display;
   private EditText edt_email,edt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        HamburgerDatabase database = HamburgerDatabase.getSNoteDatabase(MainActivity.this);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_email= edt_email.getText().toString();
                String text_password= edt_password.getText().toString();
                User user = new User();
                user.setEmail(text_email);
                user.setPassword(text_password);
                 database.userDao().insert(user);
                Toast.makeText(getApplicationContext(), "Insert thành công", Toast.LENGTH_SHORT).show();

            }

        });
        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_email= edt_email.getText().toString();

                StringBuilder stringBuilder = new StringBuilder();
                List<User> listUser = database.userDao().getAllUsers();
                for (User user : listUser){
                    stringBuilder.append(user.getEmail() +":" +user.getPassword());
                    stringBuilder.append("/n");
                }
                Toast.makeText(getApplicationContext(), stringBuilder, Toast.LENGTH_SHORT).show();

            }
        });
    }
    void Init(){
        btn_display=findViewById(R.id.button2);
        btn_save=findViewById(R.id.button);
        edt_email=findViewById(R.id.editTextText);
        edt_password=findViewById(R.id.editTextText2);

    }
}