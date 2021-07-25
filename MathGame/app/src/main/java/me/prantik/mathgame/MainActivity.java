package me.prantik.mathgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addition;
    Button subtraction;
    Button multiplication;
    Button randomly;

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
        randomly = findViewById(R.id.randomly);

        addition.setOnClickListener(view -> startGame("+"));
        subtraction.setOnClickListener(view -> startGame("-"));
        multiplication.setOnClickListener(view -> startGame("x"));
        randomly.setOnClickListener(view -> startGame("Random"));
    }

    public void startGame(String sign) {
        Intent intent =  new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("sign", sign);
        startActivity(intent);
        finish();
    }
}