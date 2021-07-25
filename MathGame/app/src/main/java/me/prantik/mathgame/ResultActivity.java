package me.prantik.mathgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView result;
    Button exit, playAgain;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setTitle("Math Game - Result");
        }

        result = findViewById(R.id.textViewResult);
        exit = findViewById(R.id.buttonExit);
        playAgain = findViewById(R.id.buttonPlayAgain);

        Intent myIntent = getIntent();
        score = myIntent.getIntExtra("score", 0);
        result.setText("Your score is " + score);

        playAgain.setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        exit.setOnClickListener(view -> {
            finish();
        });
    }
}