package com.example.manue.higherlower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int currentImageIndex = 0;
    private int previousImageIndex;
    private int[] mImageNames;
    private ImageView mImageView;
    private FloatingActionButton mUpButton;
    private FloatingActionButton mDownButton;
    private ListView mListView;
    private TextView mScore;
    private TextView mHighscore;
    private int score;
    private int highscore;
    private ArrayAdapter mAdapter;
    private List<String> mResults;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        mImageView = findViewById(R.id.imageView);
        mUpButton = findViewById(R.id.upButton);
        mDownButton = findViewById(R.id.downButton);
        mListView = findViewById(R.id.listView);
        mScore = findViewById(R.id.score);
        mHighscore = findViewById(R.id.highscore);
        mResults = new ArrayList<>();

        mUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int limitIndex = 5;
                previousImageIndex = currentImageIndex;
                Random random = new Random();
                currentImageIndex = random.nextInt(limitIndex);
                mImageView.setImageResource(mImageNames[currentImageIndex]);
                mResults.add(0, "Throw is "+(currentImageIndex+1));

                if (previousImageIndex <= currentImageIndex) {
                    Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                    score = score+1;
                    mScore.setText("Score: "+score);
                    if(score > highscore){
                        highscore = score;
                        mHighscore.setText("Highscore: "+highscore);
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    score = 0;
                    mScore.setText("Score: "+score);
                }
                updateUI();

            }
        });

        mDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int limitIndex = 5;
                previousImageIndex = currentImageIndex;
                Random random = new Random();
                currentImageIndex = random.nextInt(limitIndex);
                mImageView.setImageResource(mImageNames[currentImageIndex]);
                mResults.add(0, "Throw is "+(currentImageIndex+1));

                if (previousImageIndex >= currentImageIndex) {
                    Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                    score = score+1;
                    mScore.setText("Score: "+score);
                    if(score > highscore){
                        highscore = score;
                        mHighscore.setText("Highscore: "+highscore);
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    score = 0;
                    mScore.setText("Score: "+score);
                }
                updateUI();

            }
        });
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mResults);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

}


