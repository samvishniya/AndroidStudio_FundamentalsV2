package com.example.recyclerview;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements WordListAdapter.OnWordListener {

    private final LinkedList<String> wordList = new LinkedList<>();
    private RecyclerView recyclerView;
    private WordListAdapter adapter;


    /**
     * Note : RecyclerView in the xml should be in a relativeLayout otherwise the wrap_content for height doesnt work great
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = wordList.size();
                // add a new word to the wordlist
                wordList.addLast("+ NEEEEWWord "+ wordListSize);
                // notify adapter tat its changed so it actualy gets updated
                recyclerView.getAdapter().notifyItemInserted(wordListSize);
                // scroll to bottom
                recyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        for(int i=0; i<=20;i++) {
            wordList.addLast("Word "+i);
        }

        // create and connect the recyclerview with an adapter and data

        //1st get a handle to the recyclerview
        recyclerView = findViewById(R.id.recycler_wordList);
        // create an adapter and supply the data to be displayed
        adapter = new WordListAdapter(this,wordList,this);
        // connect the adapter to the recyclerview
        recyclerView.setAdapter(adapter);
        // finally give the recyclerview a default layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        // reset button that clears the wordlist and rebuilds it in its original state
        if (id == R.id.action_options_reset) {
            wordList.clear();

            for(int i=0; i<=20;i++) {
                wordList.addLast("Word "+i);
            }
            int wordListSize = wordList.size();
            // notify adapter tat its changed so it actualy gets updated
            Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWordClick(int position) {
        String element = wordList.get(position);
        wordList.set(position, "Clicked! " + element);
        adapter.notifyDataSetChanged();
    }
}
