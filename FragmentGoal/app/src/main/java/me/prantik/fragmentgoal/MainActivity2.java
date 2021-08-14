package me.prantik.fragmentgoal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button buttonShow;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonShow = findViewById(R.id.buttonShow);
        textViewResult = findViewById(R.id.textViewResult);

        buttonShow.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            MyDialogFragment myDialogFragment = new MyDialogFragment();

            myDialogFragment.setCancelable(false);

            myDialogFragment.show(fragmentTransaction, "MyDialogFragment");
        });
    }
}