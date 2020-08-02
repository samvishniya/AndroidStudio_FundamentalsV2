package com.example.highscoreroomdatabase2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nicknameEntry;
    private HighScoreViewModel highScoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nicknameEntry=findViewById(R.id.nickname_entry_edittext);

        RecyclerView recyclerView = findViewById(R.id.highscore_recyclerview);
        final HighScoreListAdapter adapter = new HighScoreListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        // todo fix this deprecated
        highScoreViewModel = ViewModelProviders.of(this).get(HighScoreViewModel.class);

        highScoreViewModel.getAllHighScores().observe(this, new Observer<List<HighScore>>() {
            @Override
            public void onChanged(@Nullable final List<HighScore> nicknames) {
                // Update the cached copy of the words in the adapter.
                adapter.setNicknames(nicknames);
            }
        });


    }

    public void saveHighscore(View view) {
        // creating a new highscore using input name
        // todo also use a int highscore from system
        String nickname=nicknameEntry.getText().toString();
        if(nicknameEntry!=null){
            HighScore highScore = new HighScore(nickname);
            highScoreViewModel.insert(highScore);
            Toast.makeText(this,"Your highscore is saved", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this,"A nickname must be entered in order to save the highscore", Toast.LENGTH_SHORT).show();
        }
    }
}
