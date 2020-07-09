package com.example.recyclerview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

/*
Adapter class to connect words in wordlist to textviews inside the recyclerview
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    // a linkedlist to hold the strings from the words the user adds

    private final LinkedList<String> wordList;

    // the constructor needs to intialize the word list from the data
    // then a view needs to be created for a list item by inflating the xml for a alist item - using alayoutinflater
    // a layoutfinlater reads a layout xml description and converts it into correspondng view items

    // heres the layout inflater
    private LayoutInflater inflater;

    // note the consturcotr needs a context aswell as the linkedlist of words holding the app's data
    // it then instantiates the layoutinflater for inflater and sets the wordlist to the data that's been passed in by paramter
    public WordListAdapter(Context context, LinkedList<String> wordList) {
        inflater = LayoutInflater.from(context);
        this.wordList = wordList;
    }

    // inner class to create a viewholder (also creats a separate onclicklistener for each item is an extra that lets you interact with items on thel ist)
    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        final WordListAdapter adapter;

// constructor for inner class - initalizes the adapter using the one sent from paramter,
        //also initalizes the textview using the view sent from the paramter (the word inside the layoutxml)

        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            // THIS LINE CONNECTS THE ONCLICK LISTENER, nothing works without this in theconstructor
          itemView.setOnClickListener(this);
            wordItemView = itemView.findViewById(R.id.word);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int position = getLayoutPosition();
// Use that to access the affected item in mWordList.
            String element = wordList.get(position);
// Change the word in the mWordList.
            wordList.set(position, "Clicked! " + element);
// Notify the adapter, that the data has changed so it can
// update the RecyclerView to display the data.
            adapter.notifyDataSetChanged();
        }
    }


    // this is similar to a onCreate method, it inflates the item layout and retursn a viewholder with the layout and adapter
    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(itemView, this);

    }

    // onbind connects your data to the viewholder
    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String current = wordList.get(position);
        holder.wordItemView.setText(current);

    }


    // returns size of the list
    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
