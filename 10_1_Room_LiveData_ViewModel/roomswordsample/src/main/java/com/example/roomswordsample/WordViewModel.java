package com.example.roomswordsample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.w3c.dom.Text;

import java.util.List;

/*

this provides a containment:
activity and fragments focus on drawing data on the screen

ViewModel is responsible for holding and processing all the data needed for the ui

Note: viewmodel isntances should never have context passed into them
also never store activ frag or view instances in a viewmodel

This is cos those can all get destroyed and recreated, e.g. when device rotated, you would end up with references to destroyed stuff aka memory leak

if you need context use AndroidViewModel
 */

public class WordViewModel extends AndroidViewModel {

    private WordRepository repository;
    private LiveData <List<Word>> allWords;


    public WordViewModel(@NonNull Application application) {
        super(application);
        repository= new WordRepository(application);
        allWords=repository.getAllWords();
    }

    // this getter method makes the implementation hidden from the ui
    LiveData<List<Word>> getAllWords(){
        return allWords;
    }

    // wrapper insert method calling the repos insert method
    public void insert(Word word){
        repository.insert(word);
    }

}
