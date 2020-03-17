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

public class ExamMark extends Fragment {

    private EditText currentMarkET;
    private EditText finalMarkET;
    private TextView examMarkTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exammarkfragment_layout, container, false);
        currentMarkET = view.findViewById(R.id.currentMarkEM);
        finalMarkET = view.findViewById(R.id.finalMarkEM);
        Button countExamBT = view.findViewById(R.id.embutton);
        examMarkTV = view.findViewById(R.id.examMarkTV);
        countExamBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentAverageValueEXLayout, finalMarkValueEXLayout;
                try {
                    currentMarkET.setError(null);
                    finalMarkET.setError(null);
                    currentAverageValueEXLayout = currentMarkET.getText().toString();
                    finalMarkValueEXLayout = finalMarkET.getText().toString();
                    if (TextUtils.isEmpty(currentAverageValueEXLayout)){
                        currentMarkET.setError("Поле не должно быть пустым!");
                    }
                    else if (currentAverageValueEXLayout.equals(".")) {
                        currentMarkET.setError("Не валидное значение!");
                    }
                    else if (finalMarkValueEXLayout.equals(".")) {
                        finalMarkET.setError("Не валидное значение!");
                    }
                    else if (Float.parseFloat(currentAverageValueEXLayout) > 100){
                        currentMarkET.setError("Слишком большое значение!");
                    }
                    else if (TextUtils.isEmpty(finalMarkValueEXLayout)){
                        finalMarkET.setError("Поле не должно быть пустым!");
                    }
                    else if (Float.parseFloat(finalMarkValueEXLayout)> 100) {
                        finalMarkET.setError("Слишком большое значение!");
                    }
                    else {
                        currentMarkET.setText("");
                        finalMarkET.setText("");
                        double currentAverageIntEXLayout, finalMarkIntEXLayout;
                        currentAverageIntEXLayout = Math.floor(Float.parseFloat(currentAverageValueEXLayout));
                        finalMarkIntEXLayout = Math.floor(Float.parseFloat(finalMarkValueEXLayout));
                        float examMark = (float)((finalMarkIntEXLayout - 0.6 * currentAverageIntEXLayout)/0.4);
                        if (examMark > 100)
                            examMark = 100;
                        else if (examMark < 0) {
                            examMark = 0;
                        }
                        examMarkTV.setText(String.valueOf(examMark));
                    }
                }
                catch (Exception e){
                    if (getActivity() != null) {


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
            }
        });
        return view;
    }
}
