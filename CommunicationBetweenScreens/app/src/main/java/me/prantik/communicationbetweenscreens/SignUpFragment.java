package me.prantik.communicationbetweenscreens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpFragment extends Fragment {
    private EditText editTextName, editTextEmail;
    private Button buttonSignUp;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        // Initializing EditText
        editTextName = view.findViewById(R.id.editTextUserName);
        editTextEmail = view.findViewById(R.id.editTextUserEmail);
        // Initializing sign up button
        buttonSignUp = view.findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(v -> sendUserData());

        return view;
    }

    public void sendUserData() {
        String userName = editTextName.getText().toString();
        String userEmail = editTextEmail.getText().toString();

        HomeActivity homeActivity = (HomeActivity) getActivity();

        if (userName.isEmpty() || userEmail.isEmpty()) {
            Toast.makeText(homeActivity, "Please enter you data", Toast.LENGTH_SHORT).show();
            return;
        }

        homeActivity.takeUserData(userName, userEmail);
    }
}