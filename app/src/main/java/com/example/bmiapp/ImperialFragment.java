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


public class ImperialFragment extends Fragment {

    private EditText feetTxt;
    private  EditText inchesTxt;
    private  EditText poundTxt;
    private TextView bmiResult;
    private TextView bmiCategory;


    public ImperialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imperial, container, false);

inchesTxt = view.findViewById(R.id.inchesTxt);
poundTxt = view.findViewById(R.id.poundTxt);
feetTxt = view.findViewById(R.id.feetTxt);
bmiCategory = view.findViewById(R.id.BMIcategory);
bmiResult = view.findViewById(R.id.BMIresult);

        // Log the initialization of views
        if (inchesTxt == null) Log.e("ImperialFragment", "inchesTxt is null");
        if (poundTxt == null) Log.e("ImperialFragment", "poundTxt is null");
        if (feetTxt == null) Log.e("ImperialFragment", "feetTxt is null");
        if (bmiCategory == null) Log.e("ImperialFragment", "bmiCategory is null");
        if (bmiResult == null) Log.e("ImperialFragment", "bmiResult is null");


Button btnCalculateBMI = view.findViewById(R.id.CalculateBmiBtn);
btnCalculateBMI.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){

        calculateBMiMetric();
    }
});
Button clearBtn = view.findViewById(R.id.clearBtn);
clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clearInputs();
            }
});

return view;
    }

    private void calculateBMiMetric() {
        double weight=0;
        double heightFeet=0;
        double heightInches = 0;

        try {
            weight = Double.parseDouble(poundTxt.getText().toString());
            heightInches = Double.parseDouble((inchesTxt.getText().toString()));
            heightFeet = Double.parseDouble(feetTxt.getText().toString());
        }
        catch (NumberFormatException f){
            Toast.makeText(getActivity(), "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            return;
        }
        // checking if height, weight, and age are valid
        if (heightInches <0 || weight <= 0 || heightFeet <= 0) {
            Toast.makeText(getActivity(), "height, and weight must be positive.", Toast.LENGTH_SHORT).show();
            return;
        }
        double heightInInches = (heightFeet * 12) + heightInches;

        double BMIImperial = (weight /(heightInInches * heightInInches)) * 703;
        bmiResult.setText(String.format("%.2f" ,BMIImperial));

        // determining BMI category
        String BMI_Cat;
        if (BMIImperial < 15)
            BMI_Cat = "Very severely underweight";
        else if (BMIImperial < 16)
            BMI_Cat = "Severely underweight";
        else if (BMIImperial < 18.5)
            BMI_Cat = "Underweight";
        else if (BMIImperial < 25)
            BMI_Cat = "Normal";
        else if (BMIImperial < 30)
            BMI_Cat = "Overweight";
        else if (BMIImperial < 35)
            BMI_Cat = "Obese Class 1 – Moderately Obese";
        else if (BMIImperial < 40)
            BMI_Cat = "Obese Class 2 - Severely Obese";
        else
            BMI_Cat = "Obese Class 3 - Very Severely Obese";

        bmiCategory.setText(BMI_Cat);
    }
    // method to clear input fields
    private void clearInputs() {
        feetTxt.setText("");
        inchesTxt.setText("");
        poundTxt.setText("");
        bmiCategory.setText("");
        bmiResult.setText("");
    }
}