package com.example.bmiapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    {
        // declaring variables to hold UI components
        private EditText heightText;
        private EditText weightText;
        private TextView BMIResult;
        private TextView BMICategory;

        // the method that gets called when the activity is created
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // initializing UI components
            heightText = findViewById(R.id.heighttxt);
            weightText = findViewById(R.id.weighttxt);
            BMIResult = findViewById(R.id.BMIresult);
            BMICategory = findViewById(R.id.BMIcategory);

            // setting up button click listeners
            Button BMIBtn = findViewById(R.id.CalculateBmiBtn);
            BMIBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculateBMI();
                }
        });

            Button clearBtn = findViewById(R.id.ClearBtn);
            clearBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearInputs();
                }
            });
        }
        // method to calculate BMI
        private void calculateBMI() {
            String heightStr = heightText.getText().toString();
            String weightStr = weightText.getText().toString();

            // checking if height and weight are entered
            if (TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(weightStr)) {
                Toast.makeText(this, "Please enter both height and weight.", Toast.LENGTH_SHORT).show();
                return;
            }

            double height = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            // checking if height and weight are valid
            if (height <= 0 || weight <= 0) {
                Toast.makeText(this, "Height and weight must be positive.", Toast.LENGTH_SHORT).show();
                return;
            }

            // calculating BMI and display
            double BMI = weight / (height * height);
            BMIResult.setText(String.format("%.2f", BMI));

            // determining BMI category
            String BMI_Cat;
            if (BMI < 15)
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
        // method to clear input fields
        private void clearInputs() {
            heightText.setText("");
            weightText.setText("");
            BMIResult.setText("");
            BMICategory.setText("");
        }
    }