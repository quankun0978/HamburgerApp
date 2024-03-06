package com.group.hamburgerapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.group.hamburgerapp.dao.UserDao;
import com.group.hamburgerapp.entity.User;
@Database(entities = {User.class}, version = 1)
public abstract class HamburgerDatabase extends RoomDatabase {
    private static HamburgerDatabase database;
    public static synchronized HamburgerDatabase getSNoteDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(
                    context,
                    HamburgerDatabase.class,
                    "hamburger_app"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return database;
    }
    public abstract UserDao userDao();
}


