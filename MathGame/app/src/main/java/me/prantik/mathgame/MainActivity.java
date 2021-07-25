package me.prantik.mathgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addition;
    Button subtraction;
    Button multiplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setTitle("Math Game - Home");
        }

        addition = findViewById(R.id.addition);
        subtraction = findViewById(R.id.subtraction);
        multiplication = findViewById(R.id.multiplication);

        addition.setOnClickListener(view -> startGame("Addition"));
        subtraction.setOnClickListener(view -> startGame("Subtraction"));
        multiplication.setOnClickListener(view -> startGame("Multiplication"));
    }

    public void startGame(String type) {
        Intent intent =  new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
        finish();
    }
}