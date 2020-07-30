package com.example.roomswordsample;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
// this is the roomdatabase

// annotate as a database, list all entity classes (only 1 in this db), put version num, and if you want to keep history of schema versions enable exportSchema
@Database(entities = Word.class, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    // define the DAOs working with this db
    public abstract WordDao wordDao();
    // this creates the wordroomdb as a singleton, preventing it having multiple instances open at same time
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    // this code uses rooms db builder to create a roomdb object called word_database in the app context from the wordroomdb class
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}


