package me.prantik.communicationbetweenscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class BMICalculatorActivity extends AppCompatActivity {

    private EditText editTextWeight, editTextHeight;
    private Button buttonBMI;
    private FrameLayout frame;

    // Fragments
    private FragmentManager fragmentManager;
    ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        // Initializing EditTexts
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);

        // Initializing Button
        buttonBMI = findViewById(R.id.buttonBMI);

        // Initializing FrameLayout
        frame = findViewById(R.id.frame);

        // Initializing Fragment
        fragmentManager = getSupportFragmentManager();
        resultFragment = new ResultFragment();

        // On Click listener
        buttonBMI.setOnClickListener(view -> {
            // Taking user weight and weight
            String weight = editTextWeight.getText().toString();
            String height = editTextHeight.getText().toString();

            if (!weight.isEmpty() && !height.isEmpty()) {
                calculateBMI(Double.parseDouble(weight), Double.parseDouble(height));
            } else {
                Toast.makeText(BMICalculatorActivity.this, "Enter your weight & height.", Toast.LENGTH_SHORT)
                        .show();
            }

        });
    }

    private void calculateBMI(double weight, double height) {
        // putting arguments to result Fragment
        Bundle bundle = new Bundle();

        bundle.putDouble("weight", weight);
        bundle.putDouble("height", height);

        resultFragment.setArguments(bundle);

        // adding resultFragment to fragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, resultFragment);
        fragmentTransaction.commit();
    }
}