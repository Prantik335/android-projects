package me.prantik.guessingnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast, textViewRight, textViewHint;
    private EditText editTextGuess;
    private Button buttonConfirm;

    private int randomNum, digit;
    private int remainingRights = 10;

    private void initializeViews() {
        // initializing textView(s)
        textViewLast = findViewById(R.id.textViewLast);
        textViewRight = findViewById(R.id.textViewRight);
        textViewHint = findViewById(R.id.textViewHint);

        // initializing button(s)
        buttonConfirm = findViewById(R.id.buttonConfirm);

        // initializing editText(s)
        editTextGuess = findViewById(R.id.editTextGuess);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // initialize views
        initializeViews();

        // getting data from intent
        digit = getIntent().getIntExtra("digit", 3);

        // Generating random number
        generateRandomNum();

        buttonConfirm.setOnClickListener(view -> onConfirm());

    }

    private void generateRandomNum() {
        Random random = new Random();

        if (digit == 2) {
            randomNum = random.nextInt(100);
        } else if (digit == 3) {
            randomNum = random.nextInt(900) + 100;
        } else {
            randomNum = random.nextInt(9000) + 1000;
        }
    }

    private void onConfirm() {
        String userGuessStr = editTextGuess.getText().toString();

        if (userGuessStr.isEmpty()) {
            Toast.makeText(this, "Guess the number", Toast.LENGTH_SHORT).show();
            return;
        }
        // Setting these textViews Visible
        if(textViewHint.getVisibility() == View.GONE) {
            textViewHint.setVisibility(View.VISIBLE);
            textViewLast.setVisibility(View.VISIBLE);
        }
        // parsing userGuess to integer
        int userGuess = Integer.parseInt(userGuessStr);

        // Decreasing remainingRights & setting the textView
        remainingRights--;
        textViewRight.setText("Remaining Rights: " + remainingRights);

        if (remainingRights < 0) {
            Toast.makeText(this, "Game End.", Toast.LENGTH_SHORT).show();
            finish();
        } else if (userGuess > randomNum) {
            textViewHint.setText("Decrease Your Guess!");
        } else if (userGuess < randomNum) {
            textViewHint.setText("Increase Your Guess!");
        } else {
            Toast.makeText(this, "Correct Guess!", Toast.LENGTH_SHORT).show();
        }

        textViewLast.setText("Last Guess: " + userGuessStr);
    }

}