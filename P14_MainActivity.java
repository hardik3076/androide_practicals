package com.example.p14_1;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText editInput;
    Button btnConvert;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinnerConversion);
        editInput = findViewById(R.id.editInput);
        btnConvert = findViewById(R.id.btnConvert);
        textResult = findViewById(R.id.textResult);

        String[] conversions = {
                "Celsius to Fahrenheit",
                "Fahrenheit to Celsius",
                "INR to USD",
                "USD to INR",
                "Liters to Gallons",
                "Gallons to Liters"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, conversions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editInput.getText().toString().isEmpty()) {
                    editInput.setError("Please enter a value");
                    return;
                }

                double input = Double.parseDouble(editInput.getText().toString());
                double result = 0;
                String selected = spinner.getSelectedItem().toString();

                switch (selected) {

                    case "Celsius to Fahrenheit":
                        result = (input * 9 / 5) + 32;
                        textResult.setText("Fahrenheit: " + result);
                        break;

                    case "Fahrenheit to Celsius":
                        result = (input - 32) * 5 / 9;
                        textResult.setText("Celsius: " + result);
                        break;

                    case "INR to USD":
                        result = input / 83; // Fixed rate
                        textResult.setText("USD: " + result);
                        break;

                    case "USD to INR":
                        result = input * 83;
                        textResult.setText("INR: " + result);
                        break;

                    case "Liters to Gallons":
                        result = input * 0.264;
                        textResult.setText("Gallons: " + result);
                        break;

                    case "Gallons to Liters":
                        result = input / 0.264;
                        textResult.setText("Liters: " + result);
                        break;
                }
            }
        });
    }
}
