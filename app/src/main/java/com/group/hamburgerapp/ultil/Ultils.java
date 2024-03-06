package com.group.hamburgerapp.ultil;

import android.content.Context;
import android.content.Intent;

import com.group.hamburgerapp.activity.MainActivity;

public class Ultils {
    public static   void changeIntent (Context context1 , Context context2){
        Intent intent = new Intent(context1.getApplicationContext(),context2.getClass());
        context1.startActivity(intent);
    }
}
