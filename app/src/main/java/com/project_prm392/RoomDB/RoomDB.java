package com.project_prm392.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project_prm392.Entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB instance;

    public static synchronized RoomDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), RoomDB.class).build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();
}
