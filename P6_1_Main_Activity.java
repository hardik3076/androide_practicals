package com.example.p6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView t1, tvCheckCount;
    EditText e1, e2;
    RadioButton r1, r2;
    RadioGroup rg;
    ToggleButton tb1;
    Button b1, bDetails;
    CheckBox cb1, cb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.t_1);
        e1 = findViewById(R.id.editTextText);
        e2 = findViewById(R.id.editTextTextPassword);
        rg = findViewById(R.id.radioGroup);
        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
        tb1 = findViewById(R.id.toggleButton);
        b1 = findViewById(R.id.button);
        bDetails = findViewById(R.id.buttonDetails);
        cb1 = findViewById(R.id.checkBox);
        cb2 = findViewById(R.id.checkBox2);
        tvCheckCount = findViewById(R.id.tvCheckCount);

        // Change RadioButton color when selected
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            r1.setTextColor(Color.BLACK);
            r2.setTextColor(Color.BLACK);

            if (checkedId == R.id.radioButton)
                r1.setTextColor(Color.BLUE);
            else if (checkedId == R.id.radioButton2)
                r2.setTextColor(Color.BLUE);
        });

        // Submit Button (count + clear)
        b1.setOnClickListener(view -> {

            int count = 0;
            if (cb1.isChecked()) count++;
            if (cb2.isChecked()) count++;

            tvCheckCount.setText("Selected CheckBoxes: " + count);

            cb1.setChecked(false);
            cb2.setChecked(false);
            rg.clearCheck();

            r1.setTextColor(Color.BLACK);
            r2.setTextColor(Color.BLACK);
        });

        // Login Details Button
        bDetails.setOnClickListener(view -> {

            String name = e1.getText().toString();
            String pass = e2.getText().toString();

            String gender = "Not Selected";
            if (r1.isChecked()) gender = "Male";
            else if (r2.isChecked()) gender = "Female";

            String toggleStatus = tb1.isChecked() ? "ON" : "OFF";

            Toast.makeText(MainActivity.this,
                    "Name: " + name +
                            "\nPassword: " + pass +
                            "\nGender: " + gender +
                            "\nToggle: " + toggleStatus,
                    Toast.LENGTH_LONG).show();
        });
    }
}
