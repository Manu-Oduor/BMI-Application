package com.example.bmiapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MetricFragment extends Fragment {

    private EditText kgTxt;
    private EditText cmTxt;
    private Button calculateBmiBtn;

    private  Button clearBtn;
    private TextView BMIResult;
    private TextView BMICategory;


    public MetricFragment(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.fragment_metric, container,false);

        kgTxt = view.findViewById(R.id.kgTxt);
        cmTxt = view.findViewById(R.id.cmTxt);
        BMIResult = view.findViewById(R.id.BMIresult);
        BMICategory =view.findViewById(R.id.BMIcategory);
        calculateBmiBtn = view.findViewById(R.id.CalculateBmiBtn);
        clearBtn = view.findViewById(R.id.clearBtn);

        if (kgTxt == null || cmTxt == null || BMIResult == null || BMICategory == null || calculateBmiBtn == null || clearBtn == null) {
            Log.e("MetricFragment", "One or more UI components are null");
        }

        calculateBmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMIMetric();
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
            }
        });

        return view;

    }
    private void  calculateBMIMetric(){
        double weight = 0;
        double height = 0;
        try {
            weight = Double.parseDouble(kgTxt.getText().toString());
            height = Double.parseDouble(cmTxt.getText().toString()) / 100;
        }catch (NumberFormatException e){
            Toast.makeText(getActivity(), "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(weight <= 0 || height <=0){
            Toast.makeText(getActivity(),"height and weight must be positive.",Toast.LENGTH_SHORT).show();
            return;
        }
        double BMI = weight / (height*height);

        BMIResult.setText(String.format("%.2f",BMI));

        // determining BMI category
        String BMI_Cat;
        if ( BMI< 15)
            BMI_Cat = "Very severely underweight";
        else if (BMI < 16)
            BMI_Cat = "Severely underweight";
        else if (BMI < 18.5)
            BMI_Cat = "Underweight";
        else if (BMI < 25)
            BMI_Cat = "Normal";
        else if (BMI < 30)
            BMI_Cat = "Overweight";
        else if (BMI < 35)
            BMI_Cat = "Obese Class 1 – Moderately Obese";
        else if (BMI < 40)
            BMI_Cat = "Obese Class 2 - Severely Obese";
        else
            BMI_Cat = "Obese Class 3 - Very Severely Obese";

        BMICategory.setText(BMI_Cat);
    }
    private void clearInputs() {
        kgTxt.setText("");
        cmTxt.setText("");
        BMICategory.setText("");
        BMIResult.setText("");
    }
}