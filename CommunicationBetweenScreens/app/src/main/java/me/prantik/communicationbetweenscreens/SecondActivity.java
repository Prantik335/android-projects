package me.prantik.communicationbetweenscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewName, textViewEmail, textViewPhone;
    private String userName, userEmail;
    private int userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initializing TextViews
        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPhone = findViewById(R.id.textViewPhone);

        // Getting data from intent
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        userEmail = intent.getStringExtra("userEmail");
        userPhone = intent.getIntExtra("userPhone", 0);

        // Setting user's data to screen
        textViewName.setText(userName);
        textViewEmail.setText(userEmail);
        textViewPhone.setText(String.valueOf(userPhone));
    }
}