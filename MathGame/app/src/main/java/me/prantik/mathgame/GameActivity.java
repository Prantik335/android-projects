package me.prantik.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView score, life, time, question, status, answerTextView;

    EditText answer;

    Button ok, nextQuestion;

    Random random = new Random();

    int num1, num2, userAns, correctAns;
    int userScore = 0;
    int userLife = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

        ok.setOnClickListener(view -> {
            userAns = Integer.parseInt(answer.getText().toString());

            if (userAns == correctAns) {
                userScore += 10;
                score.setText("" + userScore);

                status.setVisibility(View.VISIBLE);
                status.setText(R.string.status_correct);
                status.setTextColor(Color.GREEN);

            } else {
                status.setVisibility(View.VISIBLE);
                status.setText(R.string.status_wrong);
                status.setTextColor(Color.RED);

                userLife--;
                life.setText("" + userLife);
            }

            closeKeyboard();
            answerTextView.setVisibility(View.VISIBLE);
            answerTextView.setText("" + userAns);
            answer.setVisibility(View.GONE);
        });

        nextQuestion.setOnClickListener(view -> {

        });
    }

    public void gameContinue() {
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);

        correctAns = num1 + num2;

        question.setText(num1 + " + " + num2);
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}