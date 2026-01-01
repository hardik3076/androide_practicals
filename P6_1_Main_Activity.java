package com.example.p6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView t1;
    EditText e1, e2;
    RadioButton r1, r2;
    RadioGroup rg;
    ToggleButton tb1;
    Button b1;

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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = e1.getText().toString();
                String pass = e2.getText().toString();

                 String gender = "";
                if (r1.isChecked())
                    gender = "Male";
                else if (r2.isChecked())
                    gender = "Female";

                 String toggleStatus = tb1.isChecked() ? "ON" : "OFF";

                 if (name.equals("pvpit") && pass.equals("pvpit")) {
                    Toast.makeText(MainActivity.this,
                            "Login Successful\nGender: " + gender +
                                    "\nToggle: " + toggleStatus,
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Login Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
