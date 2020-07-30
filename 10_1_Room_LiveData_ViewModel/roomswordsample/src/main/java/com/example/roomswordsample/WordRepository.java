package com.example.roomswordsample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    // constructor to get handle to db and initializxe member variables
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }


    // wrapper method to return cached words as livedata, room executes all queries on separate thread, obesrved livedata notifies the observer when
    // data gets changed

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // wrapper for insert method
    // use asynctask to call insert on a non ui thread , or itll crash
    // todo find outwhat to use instead of asynctask now thats its deprecated
    public void insert (Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }


    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
