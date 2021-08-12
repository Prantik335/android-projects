package me.prantik.communicationbetweenscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class BMICalculatorActivity extends AppCompatActivity {

    private EditText editTextWeight, editTextHeight;
    private Button buttonBMI;
    private FrameLayout frame;

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
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ResultFragment resultFragment = new ResultFragment();

        // On Click listener
        buttonBMI.setOnClickListener(view -> {
            // Getting user weight and weight and put them on bundle
            Bundle bundle = new Bundle();

            double weight = Double.parseDouble(editTextWeight.getText().toString());
            double height = Double.parseDouble(editTextHeight.getText().toString());

            bundle.putDouble("weight", weight);
            bundle.putDouble("height", height);

            // setting arguments to resultFragment then adding to fragmentTransaction
            resultFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.frame, resultFragment);
            fragmentTransaction.commit();
        });
    }
}