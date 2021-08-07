package me.prantik.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult, textViewHistory;

    int[] buttonIds = {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnAC, R.id.btnDel, R.id.btnEqual, R.id.btnDot,
            R.id.btnDivide, R.id.btnMulti, R.id.btnMinus, R.id.btnPlus
    };

    Map<Integer, Button> buttons = new HashMap<>();

    private String number = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewHistory);

        for (int i = 0; i < buttonIds.length; i++) {
            Button button = findViewById(buttonIds[i]);
            buttons.put(buttonIds[i], button);

            if (i < 10) {
                button.setOnClickListener(view -> numberClick(button.getText().toString()));
            }
        }
    }

    public void numberClick(String num) {
        if (number == null) {
            number = num;
        } else {
            number += num;
        }

        textViewResult.setText(number);
    }
}