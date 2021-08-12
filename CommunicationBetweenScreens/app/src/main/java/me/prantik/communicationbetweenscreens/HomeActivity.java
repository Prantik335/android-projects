package me.prantik.communicationbetweenscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private TextView textViewName, textViewEmail;
    private SignUpFragment signUpFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initializing TextViews
        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);

        // Initializing signupFragment and fragmentTransaction
        signUpFragment = new SignUpFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Adding signUpFragment
        fragmentTransaction.add(R.id.frame_layout, signUpFragment);
        fragmentTransaction.commit();

    }
    // updating userData in views
    public void takeUserData(String userName, String userEmail) {
        textViewName.setText(userName);
        textViewEmail.setText(userEmail);
    }
}