package me.prantik.guessingnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private final RadioButton[] radioButtons = new RadioButton[3];

    private void initialize() {
        startButton = findViewById(R.id.buttonStart);

        radioButtons[0] = findViewById(R.id.radioButton1);
        radioButtons[1] = findViewById(R.id.radioButton2);
        radioButtons[2] = findViewById(R.id.radioButton3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        startButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);

            for (int i = 0; i < radioButtons.length; i++) {
                RadioButton radioButton = radioButtons[i];

                if (i + 1 == radioButtons.length && !radioButton.isChecked()) {
                    Snackbar.make(view, "You selected none!", Snackbar.LENGTH_SHORT).show();
                } else if (radioButton.isChecked()) {
                    intent.putExtra("digit", i + 1);
                    startActivity(intent);
                    break;
                }
            }
        });
    }
}