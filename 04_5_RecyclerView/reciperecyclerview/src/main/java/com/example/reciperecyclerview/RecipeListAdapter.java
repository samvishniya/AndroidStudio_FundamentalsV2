package com.example.reciperecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private final LinkedList<String> recipeNames;
    private final LinkedList<String> recipeDesc;
    private LayoutInflater inflater;
    private OnRecipeListener onRecipeListener;

    public RecipeListAdapter(Context context, LinkedList<String> recipeNames, LinkedList<String> recipeDesc, OnRecipeListener onRecipeListener) {
        inflater = LayoutInflater.from(context);
        this.recipeNames = recipeNames;
        this.recipeDesc = recipeDesc;
        this.onRecipeListener = onRecipeListener;
    }

    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.recipelist_item,parent,false);

        return new RecipeViewHolder(itemView,onRecipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeViewHolder holder, int position) {
        String currentTitle = recipeNames.get(position);
        String currentDesc = recipeDesc.get(position);
        holder.recipeTitleTextView.setText(currentTitle);
        holder.recipeShortDescriptionTextView.setText(currentDesc);

    }

    @Override
    public int getItemCount() {
        return recipeNames.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView recipeTitleTextView;
        public final TextView recipeShortDescriptionTextView;

        OnRecipeListener onRecipeListener;


        public RecipeViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
            super(itemView);
            this.onRecipeListener = onRecipeListener;
            recipeTitleTextView = itemView.findViewById(R.id.recipe_title);
            recipeShortDescriptionTextView = itemView.findViewById(R.id.recipe_short_description);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onRecipeListener.onRecipeClick(getAdapterPosition());
        }
    }

    public interface OnRecipeListener {
        void onRecipeClick(int position);

    }


}
