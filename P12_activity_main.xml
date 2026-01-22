package com.example.p12;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtDisplay;
    double firstValue = 0, secondValue = 0;
    String operator = "";
    boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDisplay = findViewById(R.id.txtDisplay);

        int[] numberButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        };

        View.OnClickListener numberListener = view -> {
            Button btn = (Button) view;
            if (isNewInput) {
                txtDisplay.setText("");
                isNewInput = false;
            }
            txtDisplay.append(btn.getText().toString());
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(numberListener);
        }

        findViewById(R.id.btnAdd).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnSub).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMul).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.btnDiv).setOnClickListener(v -> setOperator("/"));

        findViewById(R.id.btnEqual).setOnClickListener(v -> calculateResult());

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            txtDisplay.setText("0");
            firstValue = secondValue = 0;
            operator = "";
            isNewInput = true;
        });
    }

    private void setOperator(String op) {
        try {
            firstValue = Double.parseDouble(txtDisplay.getText().toString());
            operator = op;
            isNewInput = true;
        } catch (Exception e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateResult() {
        try {
            secondValue = Double.parseDouble(txtDisplay.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstValue + secondValue;
                    break;
                case "-":
                    result = firstValue - secondValue;
                    break;
                case "*":
                    result = firstValue * secondValue;
                    break;
                case "/":
                    if (secondValue == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = firstValue / secondValue;
                    break;
            }

            txtDisplay.setText(String.valueOf(result));
            isNewInput = true;

        } catch (Exception e) {
            Toast.makeText(this, "Error in Calculation", Toast.LENGTH_SHORT).show();
        }
    }
}
