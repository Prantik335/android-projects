package me.prantik.communicationbetweenscreens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

public class FirstFragment extends Fragment {
    EditText editTextName;
    Button buttonSend;

    public FirstFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Initializing components
        editTextName = view.findViewById(R.id.editTextFFName);
        buttonSend = view.findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(v -> sendDataToSecondFragment());

        return view;
    }

    private void sendDataToSecondFragment() {
        String name = editTextName.getText().toString();

        if(name.isEmpty()) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        SecondFragment secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction();

        fragmentTransaction.replace(R.id.frame, secondFragment).commit();
    }
}