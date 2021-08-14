package me.prantik.fragmentgoal;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MyDialogFragment extends DialogFragment {
    EditText editTextMsg;
    Button buttonCancel, buttonOk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_dialog, container, false);

        editTextMsg = view.findViewById(R.id.editTextMsg);
        buttonCancel = view.findViewById(R.id.buttonCancel);
        buttonOk = view.findViewById(R.id.buttonOk);

        Dialog dialog = getDialog();

        buttonOk.setOnClickListener(view1 -> {
            String msg = editTextMsg.getText().toString();
            ((MainActivity2) getActivity()).textViewResult.setText(msg);
            dialog.dismiss();
        });

        buttonCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });

        return view;
    }
}