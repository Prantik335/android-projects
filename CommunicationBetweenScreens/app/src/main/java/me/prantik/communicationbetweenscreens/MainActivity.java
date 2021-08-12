package me.prantik.communicationbetweenscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextEmail, editTextPhone;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing EditTexts
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);

        // Initializing Button
        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(view -> {
            // getting user's data from EditTexts
            String userName = editTextName.getText().toString();
            String userEmail = editTextEmail.getText().toString();
            int userPhone = Integer.parseInt(editTextPhone.getText().toString());
            // put data to intent
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("userName", userName)
                    .putExtra("userEmail", userEmail)
                    .putExtra("userPhone", userPhone);
            startActivity(intent);
        });
    }
}