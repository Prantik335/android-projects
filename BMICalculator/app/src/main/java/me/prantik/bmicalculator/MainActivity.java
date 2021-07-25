package me.prantik.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText weightEditText, heightEditText;
    Button calculateButton;
    TextView bmiTextView, statusTextView;
    double weight, height = 0.0; // kg, feet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.editTextWeight);
        heightEditText = findViewById(R.id.editTextHeight);

        calculateButton = findViewById(R.id.buttonCalculateBMI);

        bmiTextView = findViewById(R.id.textViewBmi);
        statusTextView = findViewById(R.id.textViewStatus);

        calculateButton.setOnClickListener(view -> {
            if (!canCalculate()) {
                Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT)
                        .show();
            } else {
                double bmi = calculateBMI();
                String status = getStatus(bmi);
                bmiTextView.setText(String.format(Locale.getDefault(), "%.1f", bmi));
                statusTextView.setText(status);
            }
        });
    }

    public double calculateBMI() {
        height *= 0.3048; // convert to meter
        double bmi = weight / (height * height);

        if (bmi < 16.0 || bmi >= 40.0) {
            return 0.0;
        }

        return bmi;
    }

    public String getStatus(double bmi) {
        if (bmi >= 16.0 && bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25.0) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi < 40.0) {
            return "Overweight";
        } else {
            return "Undefined";
        }
    }

    public boolean canCalculate() {
        String _weight = weightEditText.getText().toString();
        String _height = heightEditText.getText().toString();

        if (_weight.isEmpty() || _height.isEmpty()) {
            return false;
        } else {
            weight = Double.parseDouble(_weight);
            height = Double.parseDouble(_height);

            return true;
        }
    }
}