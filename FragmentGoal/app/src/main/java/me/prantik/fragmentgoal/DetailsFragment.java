package me.prantik.fragmentgoal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    private TextView textViewCountry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        textViewCountry = view.findViewById(R.id.textViewCountry);

        int pos = getArguments().getInt("pos", 0);

        textViewCountry.setText(getResources().getStringArray(R.array.cites)[pos]);

        return view;
    }
}