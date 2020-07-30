package com.example.roomswordsample;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// the adapter caches data and populates the recyclerview with it
// tyhe inner class wordviewholder holds and maages a view for one list item

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater inflater;
    private List<Word> words; //cached copy of words

    public WordListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item,parent,false);
        return new WordViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (words != null) {
            Word current = words.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    void setWords(List<Word> words){
        this.words=words;
        notifyDataSetChanged();
    }

    // this is called many times, and when it is first called,
    // words has not been updated yet (meaning its initally null, this is aproblem cos we cant return null)
    @Override
    public int getItemCount() {
        if(words!=null){
            return words.size();
        }
        else{
            // you could display placeholder data in case of  a null
        return 0;
        }
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView= itemView.findViewById(R.id.textView);
        }
    }
}
