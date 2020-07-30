package com.example.roomswordsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
This class acts as the entity for a Room database
You need to annotate everything in order for the Room infrastrucutre to identify how each part is mean to relate to an entry in the database
Room uses this annotations to create code+
all fields s tored in the db must be public or have getter method
 */
// @Entity indicates this class is an entity, tableName can be named here ,o therwise itll default to class name
@Entity (tableName = "word_table") public class Word  {

    // primary keys are needed for every entity,
    // for simplicity eaech word in this sample app acts as its own primary key
    // the primary key should always sue the nonnull annotation

    // nonnull should also be used for any mandatory fields in ur rows
    // @columninfo is used to specify the name of a coliumn in the table, defaults to the name of the member variable
    @PrimaryKey @NonNull @ColumnInfo(name = "wordoo")
    private String word;
    // nonnull means paramters can never be null
    public Word (@NonNull String word){
        this.word=word;
    }

    public String getWord(){
        return word;
    }

}
