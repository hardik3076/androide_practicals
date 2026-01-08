package com.example.p8_1;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    RadioGroup radioGroupGender;
    CheckBox checkRemember;
    Button btnLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkRemember = findViewById(R.id.checkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

         TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnLogin.setEnabled(
                        !etUsername.getText().toString().trim().isEmpty() &&
                                !etPassword.getText().toString().trim().isEmpty() &&
                                radioGroupGender.getCheckedRadioButtonId() != -1
                );
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        etUsername.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

        radioGroupGender.setOnCheckedChangeListener((group, checkedId) -> btnLogin.setEnabled(
                !etUsername.getText().toString().trim().isEmpty() &&
                        !etPassword.getText().toString().trim().isEmpty()
        ));

        btnLogin.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);

            new Handler().postDelayed(() -> {
                progressBar.setVisibility(View.GONE);

                 int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                RadioButton selectedGender = findViewById(selectedGenderId);
                String gender = selectedGender.getText().toString();

                 if (checkRemember.isChecked()) {
                    Toast.makeText(this, "Login Successful. Remember Me enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Login Successful. Remember Me disabled", Toast.LENGTH_SHORT).show();
                }

                 Toast.makeText(this, "Gender: " + gender, Toast.LENGTH_SHORT).show();

            }, 2000);          });
    }
}
