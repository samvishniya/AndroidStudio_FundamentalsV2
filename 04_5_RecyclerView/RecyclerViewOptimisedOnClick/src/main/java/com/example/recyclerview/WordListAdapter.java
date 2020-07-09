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
    private OnWordListener onWordListener;
    private LayoutInflater inflater;

    public WordListAdapter(Context context, LinkedList<String> wordList, OnWordListener onWordListener) {
        inflater = LayoutInflater.from(context);
        this.wordList = wordList;
        this.onWordListener = onWordListener;
    }


    // this is similar to a onCreate method, it inflates the item layout and retursn a viewholder with the layout and adapter
    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(itemView, onWordListener);

    }

    // onbind connects your data to the viewholder
    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String current = wordList.get(position);
        holder.wordItemView.setText(current);

    }

// EACH of the parts of a recyclerview are their own viewholder,
// so this gets implemented in each of the words in the wordlist
    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        OnWordListener onWordListener;

// for this optimised version you are using an  interface for the custom listener, so the viewholder needs to accept your custom listener
        // this is instead of a wordlistadapter

        // we also need to tell the viewholder what the onWordListener actually is, do this by instantiating it in the outside-adapter-class
        public WordViewHolder(@NonNull View itemView, OnWordListener onWordListener) {
            super(itemView);
            this.onWordListener = onWordListener;
            wordItemView = itemView.findViewById(R.id.word);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onWordListener.onWordClick(getAdapterPosition());

        }
    }


    // returns size of the list
    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public interface OnWordListener {
        void onWordClick(int position);
    }

}
