package me.prantik.communicationbetweenscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HomeActivity extends AppCompatActivity {

    private TextView textViewName, textViewEmail;
    private SignUpFragment signUpFragment;
    private FragmentTransaction fragmentTransaction;

    private ToggleButton toggleButton;
    private boolean isSecure = false;

    private static final int FLAG_SECURE = WindowManager.LayoutParams.FLAG_SECURE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnClickListener(view -> {
            if(isSecure) {
                isSecure = false;
                toggleButton.setChecked(false);
                doNormal();
            } else {
                isSecure = true;
                toggleButton.setChecked(true);
                doSecure();
            }
        });

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

    private void doSecure() {
        getWindow().setFlags(FLAG_SECURE, FLAG_SECURE);
    }

    private void doNormal() {
        getWindow().clearFlags(FLAG_SECURE);
    }
}