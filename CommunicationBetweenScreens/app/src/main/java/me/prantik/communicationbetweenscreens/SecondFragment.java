package me.prantik.communicationbetweenscreens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class SecondFragment extends Fragment {

    TextView textViewName;

    public SecondFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        textViewName = view.findViewById(R.id.textViewData);

        Bundle bundle = getArguments();
        String name = bundle != null ? bundle.getString("name") : "?";


        textViewName.setText("Your name is: " + name);

        return view;
    }
}