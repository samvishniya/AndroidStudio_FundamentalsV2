package com.example.highscoreroomdatabase2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = HighScore.class, version = 2, exportSchema = false)
public abstract class HighScoreRoomDatabase extends RoomDatabase {

    public abstract HighScoreDAO highScoreDAO();




}
