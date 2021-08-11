package me.prantik.guessingnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    private TextView textViewLast, textViewRight, textViewHint;
    private EditText editTextGuess;
    private Button buttonConfirm;

    private int randomNum, digit;
    private int remainingRights = 10;

    private final ArrayList<Integer> userGuesses = new ArrayList<>();

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

        // focus on editTextGuess
        editTextGuess.requestFocus();

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

        Log.d(TAG, "Random Number = " + randomNum);
    }

    private void onConfirm() {
        String userGuessStr = editTextGuess.getText().toString();

        if (userGuessStr.isEmpty()) {
            Toast.makeText(this, "Guess the number", Toast.LENGTH_SHORT).show();
            return;
        }
        // Setting these textViews Visible
        if (textViewHint.getVisibility() != View.VISIBLE) {
            textViewHint.setVisibility(View.VISIBLE);
            textViewLast.setVisibility(View.VISIBLE);
            textViewRight.setVisibility(View.VISIBLE);
        }
        // parsing userGuess to integer then adding to userGuesses
        int userGuess = Integer.parseInt(userGuessStr);
        userGuesses.add(userGuess);
        // Decreasing remainingRights & setting the textView
        remainingRights--;
        textViewRight.setText("Remaining Rights: " + remainingRights);

        if (remainingRights < 1) {
            finishGame(false);
            textViewHint.setText("Your guess is wrong!");
        } else if (userGuess > randomNum) {
            textViewHint.setText("Decrease Your Guess!");
        } else if (userGuess < randomNum) {
            textViewHint.setText("Increase Your Guess!");
        } else {
            finishGame(true);
            textViewHint.setText("Your guess is correct!");
        }

        // Setting Last guess & editTextGuess to empty
        textViewLast.setText("Last Guess: " + userGuessStr);
        editTextGuess.setText("");
    }

    private void finishGame(boolean isWon) {
        String msg;
        if (isWon) {
            msg = "Congratulations! You won.\n\n" +
                    "The number was " + randomNum + ".\n" +
                    "After " + (10 - remainingRights) + " Attempts, you guessed the number.\n\n" +
                    "Your guessing numbers : " + userGuesses.toString() + "\n\n" +
                    "Are you want to play again?\n";
        } else {
            msg = "Sorry! You lose.\n\n" +
                    "The number was " + randomNum + ".\n" +
                    "You can't guess the correct number.\n\n" +
                    "Your guessing numbers : " + userGuesses.toString() + "\n\n" +
                    "Are you want to play again?\n";
        }
        // closing the keyboard
        showOrHideKeyboard(false);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Guessing Number Game");
        dialog.setMessage(msg);
        dialog.setPositiveButton("Yes", (dialogInterface, i) -> {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        });
        dialog.setNegativeButton("No", (dialogInterface, i) -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });
        dialog.create().show();
    }

    public void showOrHideKeyboard(boolean show) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(!show) {
            /* hide keyboard */
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        } else {
            /* show keyboard */
            imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }
}