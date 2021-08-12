package me.prantik.communicationbetweenscreens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


class ResultFragment extends Fragment {
    TextView result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        // initializing result TextView
        result = view.findViewById(R.id.textViewResult);

        // taking arguments
        Bundle bundle = getArguments();
        if (bundle == null) return view;

        double weight = bundle.getDouble("weight");
        double height = bundle.getDouble("height");

        // bmi = weight (gram) / height ^ 2
        double bmi = (weight * 1000) / Math.pow(height, 2);
        // setting bmi to result TextView
        result.setText(String.valueOf(bmi));

        return view;
    }
}