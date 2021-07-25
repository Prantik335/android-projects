package me.prantik.mathgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView score, life, time, question, status, answerTextView;

    EditText answer;

    Button ok, nextQuestion;

    Random random = new Random();

    int num1, num2, userAns, correctAns;
    int userScore = 0;
    int userLife = 3;

    String type;

    CountDownTimer timer;
    private static long START_TIMER_IN_MILS;
    Boolean timer_running;
    long time_left_in_mils = START_TIMER_IN_MILS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ActionBar actionBar = getSupportActionBar();
        Intent thisIntent = getIntent();
        type = thisIntent.getStringExtra("type");

        if (actionBar != null) {
            actionBar.setTitle("Math Game - " + type);
        }

        setTimeConst();

        score = findViewById(R.id.textViewScore);
        life = findViewById(R.id.textViewLife);
        time = findViewById(R.id.textViewTime);

        question = findViewById(R.id.textViewQuestion);
        answerTextView = findViewById(R.id.answer);
        status = findViewById(R.id.textViewStatus);

        answer = findViewById(R.id.editTextAns);

        ok = findViewById(R.id.buttonOk);
        nextQuestion = findViewById(R.id.buttonNextQuestion);

        gameContinue();

        ok.setOnClickListener(view -> onOkay());

        nextQuestion.setOnClickListener(view -> {
            if (userLife < 1) {
                pauseTimer();
                Toast.makeText(getApplicationContext(), "Game Over!", Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                intent.putExtra("score", userScore);
                startActivity(intent);
                finish();
            } else {
                gameContinue();
            }
        });
    }

    public void onOkay() {
        userAns = Integer.parseInt(answer.getText().toString());

        pauseTimer();

        closeKeyboard();

        if (userAns == correctAns) {
            // update status to correct
            status.setText(R.string.status_correct);
            status.setTextColor(Color.GREEN);
            // add 10 to score
            userScore += 10;
            score.setText("" + userScore);
        } else {
            // update status to wrong
            status.setText(R.string.status_wrong);
            status.setTextColor(Color.RED);
            // decrease life
            userLife--;
            life.setText("" + userLife);
        }

        answer.setEnabled(false);
        ok.setEnabled(false);

    }

    public void gameContinue() {
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);

        String sign;

        switch (type) {
            case "Addition":
                correctAns = num1 + num2;
                sign = "+";
                break;
            case "Subtraction":
                correctAns = num1 - num2;
                sign = "-";
                break;
            case "Multiplication":
                correctAns = num1 * num2;
                sign = "x";
                break;
            default:
                sign = "";

        }
        question.setText(num1 + " " + sign + " " + num2);

        status.setText("");

        answer.setText("");
        answer.setEnabled(true);
        ok.setEnabled(true);

        pauseTimer();
        resetTimer();
        startTimer();
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void startTimer() {
        timer = new CountDownTimer(time_left_in_mils, 1000) {
            @Override
            public void onTick(long l) {
                time_left_in_mils = l;
                updateText();
            }

            @Override
            public void onFinish() {
                onTimeUp();
            }
        }.start();

        timer_running = true;
    }

    public void onTimeUp() {
        timer_running = false;
        status.setText(R.string.status_time_up);
        status.setTextColor(Color.RED);

        userLife--;
        life.setText("" + userLife);

        answer.setEnabled(false);
        ok.setEnabled(false);
    }

    private void updateText() {
        int second = (int) (time_left_in_mils / 1000) % 60;
        String time_left = String.format(Locale.getDefault(), "%02ds", second);
        time.setText(time_left);
    }

    private void resetTimer() {
        time_left_in_mils = START_TIMER_IN_MILS;
        updateText();
    }

    private void pauseTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer_running = false;
    }

    private void setTimeConst() {
        switch (type) {
            case "Addition":
                START_TIMER_IN_MILS = 10000;
                break;
            case "Subtraction":
                START_TIMER_IN_MILS = 11000;
                break;
            case "Multiplication":
                START_TIMER_IN_MILS = 20000;
                break;
            default:
        }
    }

}