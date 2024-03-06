package com.group.hamburgerapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.io.Serializable;
@Entity(tableName = "user")
public  class User implements Serializable  {
    @PrimaryKey(autoGenerate = true)
   private int id;
   private String email,password,name,address,gender,phone,date;
   @Ignore
   public User(){
   }
    public User(int id, String email, String password, String name, String address, String gender, String phone, String date) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
        this.date = date;
    }
//    @ColumnInfo(name = "userId")
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
