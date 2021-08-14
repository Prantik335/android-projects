package me.prantik.fragmentgoal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonReplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonReplace = findViewById(R.id.buttonReplace);

        // Adding FirstFragment to activity_main
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FirstFragment firstFragment = new FirstFragment();

        fragmentTransaction.add(R.id.frame, firstFragment).commit();

        buttonReplace.setOnClickListener(view -> {
            SecondFragment secondFragment = new SecondFragment();

            FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
            fragmentTransaction2.replace(R.id.frame, secondFragment).addToBackStack(null).commit();
        });
    }
}