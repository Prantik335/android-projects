package me.prantik.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
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

    private String number = null, status = null;

    private String history, currentResult;

    private double firstNum = 0, lastNum = 0;

    private boolean operator = false, equalClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewHistory);

        init();

        for (int i = 0; i < buttonIds.length; i++) {
            Button button = findViewById(buttonIds[i]);
            buttons.put(buttonIds[i], button);

            if (i < 10) {
                button.setOnClickListener(view -> numberClick(button.getText().toString()));
            } else if (i >= 14) {
                button.setOnClickListener(view -> {
                    signClick(button.getText().toString());
                });
            } else if (buttonIds[i] == R.id.btnDot) {
                button.setOnClickListener(view -> {
                    number = textViewResult.getText().toString();
                    if (!number.contains(".")) number += ".";
                    textViewResult.setText(number);
                });
            }
        }

        buttons.get(R.id.btnAC).setOnClickListener(view -> {
            allClear();
        });

        buttons.get(R.id.btnDel).setOnClickListener(view -> {
            if (number == null || number.isEmpty()) {
                number = "0";
            } else {
                number = number.substring(0, number.length() - 1);
            }
            textViewResult.setText(number);
        });

        buttons.get(R.id.btnEqual).setOnClickListener(view -> {
            signClick("");
            operator = true;
            equalClicked = true;
            firstNum = getResultValue();
        });

    }

    public void numberClick(String num) {
        if (equalClicked) {
            allClear();
            equalClicked = false;
        }
        if (number == null || number.equals("0")) {
            number = num;
        } else {
            number += num;
        }

        textViewResult.setText(number);
        operator = true;
    }

    public void signClick(String _status) {
        if (operator) {
            if(equalClicked) {
                textViewHistory.setText(textViewResult.getText().toString() + _status);
                textViewResult.setText("0");
                equalClicked = false;
            } else updateHistory(_status);

            if (status == null) status = _status;

            switch (status) {
                case "x":
                    multiply();
                    break;
                case "/":
                    divide();
                    break;
                case "-":
                    minus();
                    break;
                default:
                    plus();
            }
        }
        updateResultTextView();
        status = _status;
        operator = false;
        number = null;
    }

    public void allClear() {
        number = null;
        status = null;
        firstNum = 0;
        lastNum = 0;
        textViewHistory.setText("");
        textViewResult.setText("0");
    }

    public void plus() {
        lastNum = getResultValue();
        firstNum += lastNum;
    }

    public void minus() {
        if (firstNum == 0) {
            firstNum = getResultValue();
        } else {
            lastNum = getResultValue();
            firstNum -= lastNum;
        }
    }

    public void multiply() {
        if (firstNum == 0) {
            firstNum = getResultValue();
        } else {
            lastNum = getResultValue();
            firstNum *= lastNum;
        }
    }

    public void divide() {
        if (firstNum == 0) {
            firstNum = getResultValue();
        } else {
            lastNum = getResultValue();
            firstNum /= lastNum;
        }
    }

    public void updateHistory(String status) {
        history = textViewHistory.getText().toString();
        currentResult = textViewResult.getText().toString();
        history += currentResult + status;

        textViewHistory.setText(history);
    }

    public double getResultValue() {
        return Double.parseDouble(textViewResult.getText().toString());
    }

    public void updateResultTextView() {
        DecimalFormat formatter = new DecimalFormat("######.######");
        textViewResult.setText(formatter.format(firstNum));
    }

    public void init() {
        SharedPreferences sharedPreferences = new SharedPreferencesHelper(this).sharedPreferences;

        number = sharedPreferences.getString("number", null);
        status = sharedPreferences.getString("status", null);
        history = sharedPreferences.getString("history", "");
        currentResult = sharedPreferences.getString("result", "0");
        firstNum = sharedPreferences.getFloat("firstNum", 0);
        operator = sharedPreferences.getBoolean("operator", false);
        equalClicked = sharedPreferences.getBoolean("equalClicked", false);

        updateResultTextView();
        textViewHistory.setText(history);
    }

    @Override
    protected void onPause() {
        super.onPause();

        new SharedPreferencesHelper(this).saveData(number, status, history, currentResult, firstNum, operator, equalClicked);
    }
}