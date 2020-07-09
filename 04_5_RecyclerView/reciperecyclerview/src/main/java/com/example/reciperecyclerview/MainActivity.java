package com.example.reciperecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.LinkedList;

/*
 * Q1 With a RecyclerView you can make it so that liste items look different.
 * Q2 The primary component you need to provide to an adapter, a view item and its position with a recyclerView is...
 * ... a RecyclerView.ViewHolder - containing your view
 * Q3 In order to listen and respond to use clicks in a recyclerview you need ....
 * ... to implement the interface: View.onClickListener
 */



public class MainActivity extends AppCompatActivity implements RecipeListAdapter.OnRecipeListener {

    public  static final String TAG = "TAG";
    private final LinkedList<String> recipeNames = new LinkedList<>();
    private final LinkedList<String> recipeDesc = new LinkedList<>();
    private RecyclerView recyclerView;
    private RecipeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFile(getResources().openRawResource(R.raw.recipe_names), recipeNames);
        readFile(getResources().openRawResource(R.raw.recipe_desc), recipeDesc);
        // sort out recyclerview here

        recyclerView = findViewById(R.id.recyclerview);

        adapter = new RecipeListAdapter(this, recipeNames, recipeDesc, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    // todo add intentextra to get the correct recipe somehow
    @Override
    public void onRecipeClick(int position) {
        Intent intent = new Intent(this, RecipeActivity.class);

        String recipeName = recipeNames.get(position);
        intent.putExtra(TAG,recipeName);



        startActivity(intent);

    }


    private void readFile(InputStream ins, LinkedList<String> list) {
        String contents = null;
        String[] contents_array = null;
        int i;
        try {
            contents = IOUtils.toString(ins);
            System.out.println(contents);
            IOUtils.closeQuietly(ins);
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }

        if (contents != null) {
            contents_array = contents.split("\\r?\\n");
        }
        if (contents_array != null) {
            for (i = 0; i < contents_array.length; i++) {
                list.add(i, contents_array[i]);
                Log.d("ARRAY CONTENTS", contents_array[i]);

            }
        }
    }


}
