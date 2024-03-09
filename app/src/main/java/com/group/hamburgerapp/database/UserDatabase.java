package com.group.hamburgerapp.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.hamburgerapp.entity.User;

public class UserDatabase {
    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public static void writeNewUser(String id, String name, String email,String phone,String address,String password) {
        User user = new User(id,name,email,phone,address,password);
        mDatabase.child("user").child(id).setValue(user);
    }
}
