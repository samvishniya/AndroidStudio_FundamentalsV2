package com.example.roomswordsample;

/*

This acts as the DAO for our database
USes annotations,
Acts like a waiter aka an API between our java code and the datbase
Must be abstract class or interface
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao public interface WordDao {

    // you dont need to write sql queries urself, common ones have unique annotations
    // @insert does the work for you for inserting a word
  @Insert
  void insert(Word word);

  // theres no convenience annotation to delete multiple entries, so put the generic @query
    // followed by the desired sql code
    @Query("DELETE FROM word_table")
    void deleteAll();

    // custom query: get all the words from table and sorts them alphabetically
    // wordoo is the primary_key name from the Word table created in Word class

    // Also we've wrapped our returned data-type with LiveData , this allows you to observe changes in data aka data observation
    // so now room will generate all necessary code to update the LiveData when the db is updated
    @Query("SELECT * from word_table ORDER BY wordoo asc")
    LiveData<List<Word>> getAllWords();

}
