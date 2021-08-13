package me.prantik.communicationbetweenscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        FirstFragment firstFragment = new FirstFragment();

        fragmentTransaction.add(R.id.frame, firstFragment).commit();
    }
}