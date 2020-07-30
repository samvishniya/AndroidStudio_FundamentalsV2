package com.example.roomswordsample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
// this is the roomdatabase

// annotate as a database, list all entity classes (only 1 in this db), put version num, and if you want to keep history of schema versions enable exportSchema

// in this sample app eveytime db is opened all content is deleted an drepopd

@Database(entities = Word.class, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    // define the DAOs working with this db
    public abstract WordDao wordDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        // this overriden onopen we tell to delete all content and repop the db whenver its started
        // you cant do room db operations on the ui thread, so we create and execute an asynctask to add content to db
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // see inner clasa t bottom of file
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


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
                            // this callback is defined in this class, it populates the db in the background
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

// populate db in background
    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void> {

      private final WordDao mDao;
      String[] words = {"dolphin", "crocodile", "cobra"};
        public PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // starts the app with a clean db everytime
            // not needed if you only pop the db when its first created
            mDao.deleteAll();

            for(int i=0; i<=words.length -1;i++) {
                Word word = new Word(words[i]);
                mDao.insert(word);
            }

            return null;
        }
    }
}


