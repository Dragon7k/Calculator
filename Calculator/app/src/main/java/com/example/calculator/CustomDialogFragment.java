package com.example.calculator;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;


public class CustomDialogFragment extends DialogFragment {

    String title="";
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle(title)
                .setMessage("Для закрытия окна нажмите ОК")
                .setPositiveButton("OK", null)
                .create();

    }


}