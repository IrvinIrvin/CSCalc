package com.dbuster.cscalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class FinalMark extends Fragment {
    private EditText currentMarkET;
    private EditText examMarkET;
    private Button countFinalBtn;
    private TextView finalMarkTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finalmarkfragment_layout, container, false);
        currentMarkET = view.findViewById(R.id.currentMarkFM);
        examMarkET = view.findViewById(R.id.examMarkFM);
        countFinalBtn = view.findViewById(R.id.fmbutton);
        finalMarkTV = view.findViewById(R.id.finalMarkTV);
        countFinalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentAverageValueFMLayout, examValueFMLayout;
                try {
                    currentMarkET.setError(null);
                    examMarkET.setError(null);
                    currentAverageValueFMLayout = currentMarkET.getText().toString();
                    examValueFMLayout = examMarkET.getText().toString();
                    if (TextUtils.isEmpty(currentAverageValueFMLayout)) {
                        currentMarkET.setError("Поле не должно быть пустым!");
                    }
                    else if (Float.parseFloat(currentAverageValueFMLayout) > 100) {
                        currentMarkET.setError("Слишком большое значение!");
                    }
                    else if (TextUtils.isEmpty(examValueFMLayout)) {
                        examMarkET.setError("Поле не должно быть пустым!");
                    }
                    else if (Float.parseFloat(examValueFMLayout) > 100) {
                        examMarkET.setError("Слишком большое значение!");
                    }
                    else {
                        currentMarkET.setText("");
                        examMarkET.setText("");
                        double currentAverageIntFMLayout, examIntFMLayout;
                        currentAverageIntFMLayout = Math.floor(Float.parseFloat(currentAverageValueFMLayout));
                        examIntFMLayout = Math.floor(Float.parseFloat(examValueFMLayout));
                        float finalMark = (float) (currentAverageIntFMLayout * 0.6 + examIntFMLayout * 0.4);
                        if (finalMark > 100)
                            finalMark = 100;
                        else if (finalMark < 0)
                            finalMark = 0;
                        finalMarkTV.setText(String.valueOf(finalMark));
                    }
                }
                catch (Exception e){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage(e.toString());
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });
        return view;
    }
}
