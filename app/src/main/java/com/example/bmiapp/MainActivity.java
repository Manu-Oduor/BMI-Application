package com.example.bmiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    // declaring variables to hold UI components
    private RadioGroup radioGroup;

    // the method that gets called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing UI components
        radioGroup = findViewById(R.id.radioGroup);

        if (radioGroup == null) {
            Log.e("MainActivity", "radioGroup is null");
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });

        switchFragment(radioGroup.getCheckedRadioButtonId());
    }

    private void switchFragment(int checkedId) {
        Fragment fragment;
        if (checkedId == R.id.radioButtonMetric) {
            fragment = new MetricFragment();
        } else if (checkedId == R.id.radioButtonImperial) {
            fragment = new ImperialFragment();
        } else {
            fragment = new MetricFragment(); // Default fragment to display
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                commit();
    }
}
