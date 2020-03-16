package com.dbuster.cscalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Layouts--------------------------------------------
    RelativeLayout EXLayout, FMLayout;
    // Options--------------------------------------------
    boolean calcModIsUnChecked = true;
    TextView finalMarkOption, examMarkOption;
    Switch calcMod;
    //final mark layout------------------------------------
    TextView finalMarkTextViewFMLayout;
    EditText currentAverageEditTextFMLayout, examEditTextFMLayout;
    //exam mark layout-------------------------------------
    TextView examMarkTextViewEXLayout;
    EditText currentAverageEditTextEXLayout, finalMarkEditTextEXLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Layouts
        FMLayout = findViewById(R.id.FMLayout);
        EXLayout = findViewById(R.id.EXLayout);
        // Options
        finalMarkOption = findViewById(R.id.finalMarkOption);
        examMarkOption = findViewById(R.id.examMarkOption);
        calcMod = findViewById(R.id.calcMod);
        calcMod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // Switch не работал если на него не триггернуть его. Смена места проверки состояния на это помогло
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){ // if checked - go to EXLayout
                    calcModIsUnChecked = false;
                    EXLayout.setVisibility(View.VISIBLE);
                    FMLayout.setVisibility(View.GONE);
                    finalMarkOption.setTypeface(null, Typeface.NORMAL);
                    examMarkOption.setTypeface(null, Typeface.BOLD);
                }
                else{
                    calcModIsUnChecked = true;
                    EXLayout.setVisibility(View.GONE);
                    FMLayout.setVisibility(View.VISIBLE);
                    finalMarkOption.setTypeface(null, Typeface.BOLD);
                    examMarkOption.setTypeface(null, Typeface.NORMAL);
                }
            }
        });
        //Final mark layout-------------------------------------------------------------------
        finalMarkTextViewFMLayout = findViewById(R.id.finalMarkTextViewFMLayout);
        currentAverageEditTextFMLayout = findViewById(R.id.currentAverageEditTextFMLayout);
        examEditTextFMLayout = findViewById(R.id.examEditTextFMLayout);
        //Exam mark layout---------------------------------------------------------------------
        examMarkTextViewEXLayout = findViewById(R.id.examMarkTextViewEXLayout);
        currentAverageEditTextEXLayout = findViewById(R.id.currentAverageEditTextEXLayout);
        finalMarkEditTextEXLayout = findViewById(R.id.finalMarkEditTextEXLayout);
        finalMarkOption.setTypeface(null, Typeface.BOLD);
        examMarkOption.setTypeface(null, Typeface.NORMAL);
        EXLayout.setVisibility(View.GONE);
        FMLayout.setVisibility(View.VISIBLE);
    }
    public void countFinal(View v) {
        String currentAverageValueFMLayout, examValueFMLayout;
        String currentAverageValueEXLayout, finalMarkValueEXLayout;
        if (calcModIsUnChecked){
            try {
                currentAverageEditTextFMLayout.setError(null);
                examEditTextFMLayout.setError(null);
                currentAverageValueFMLayout = currentAverageEditTextFMLayout.getText().toString();
                examValueFMLayout = examEditTextFMLayout.getText().toString();
                if (TextUtils.isEmpty(currentAverageValueFMLayout)) {
                    currentAverageEditTextFMLayout.setError("Поле не должно быть пустым!");
                }
                else if (Float.parseFloat(currentAverageValueFMLayout) > 100) {
                    currentAverageEditTextFMLayout.setError("Слишком большое значение!");
                }
                else if (TextUtils.isEmpty(examValueFMLayout)) {
                    examEditTextFMLayout.setError("Поле не должно быть пустым!");
                }
                else if (Float.parseFloat(examValueFMLayout) > 100) {
                    examEditTextFMLayout.setError("Слишком большое значение!");
                }
                else {
                    currentAverageEditTextFMLayout.setText("");
                    examEditTextFMLayout.setText("");
                    double currentAverageIntFMLayout, examIntFMLayout;
                    currentAverageIntFMLayout = Math.floor(Float.parseFloat(currentAverageValueFMLayout));
                    examIntFMLayout = Math.floor(Float.parseFloat(examValueFMLayout));
                    float finalMark = (float) (currentAverageIntFMLayout * 0.6 + examIntFMLayout * 0.4);
                    if (finalMark > 100)
                        finalMark = 100;
                    else if (finalMark < 0)
                        finalMark = 0;
                    finalMarkTextViewFMLayout.setText(String.valueOf(finalMark));
                }
            }
            catch (Exception e){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
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
        else {
            try {
                currentAverageEditTextEXLayout.setError(null);
                finalMarkEditTextEXLayout.setError(null);
                currentAverageValueEXLayout = currentAverageEditTextEXLayout.getText().toString();
                finalMarkValueEXLayout = finalMarkEditTextEXLayout.getText().toString();
                if (TextUtils.isEmpty(currentAverageValueEXLayout)){
                    currentAverageEditTextEXLayout.setError("Поле не должно быть пустым!");
                }
                else if (Float.parseFloat(currentAverageValueEXLayout) > 100){
                    currentAverageEditTextEXLayout.setError("Слишком большое значение!");
                }
                else if (TextUtils.isEmpty(finalMarkValueEXLayout)){
                    finalMarkEditTextEXLayout.setError("Поле не должно быть пустым!");
                }
                else if (Float.parseFloat(finalMarkValueEXLayout)> 100) {
                    finalMarkEditTextEXLayout.setError("Слишком большое значение!");
                }
                else {
                    currentAverageEditTextEXLayout.setText("");
                    finalMarkEditTextEXLayout.setText("");
                    double currentAverageIntEXLayout, finalMarkIntEXLayout;
                    currentAverageIntEXLayout = Math.floor(Float.parseFloat(currentAverageValueEXLayout));
                    finalMarkIntEXLayout = Math.floor(Float.parseFloat(finalMarkValueEXLayout));
                    float examMark = (float)((finalMarkIntEXLayout - 0.6 * currentAverageIntEXLayout)/0.4);
                    if (examMark > 100)
                        examMark = 100;
                    else if (examMark < 0) {
                        examMark = 0;
                    }
                    examMarkTextViewEXLayout.setText(String.valueOf(examMark));
                }
            }
            catch (Exception e){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
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
}
