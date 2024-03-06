package com.group.hamburgerapp.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.group.hamburgerapp.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM USER")
    List<User> getAllUsers();
    @Insert
    void insert(User user);
    @Query("SELECT * FROM USER WHERE email == :email")
    User getAccountByEmail(String email);
    @Query("SELECT * FROM USER WHERE ID == :id")
    User getAccountById(int id);
    @Update
    void update(User account);
    @Insert
    void registerAccount(User user);
    @Query("select * from USER where email=(:email) and password=(:password)")
    User login(String email, String password);
}