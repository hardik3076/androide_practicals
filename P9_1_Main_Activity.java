package com.example.p9_1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone;
    RadioGroup radioGender;
    CheckBox cbTerms;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        radioGender = findViewById(R.id.radioGender);
        cbTerms = findViewById(R.id.cbTerms);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> validateAndRegister());
    }

    private void validateAndRegister() {

        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showCustomToast("All fields are required", false);
            return;
        }

        if (phone.length() != 10) {
            showCustomToast("Phone must be 10 digits", false);
            return;
        }

        if (!cbTerms.isChecked()) {
            showCustomToast("Accept Terms & Conditions", false);
            return;
        }

        int selectedId = radioGender.getCheckedRadioButtonId();
        if (selectedId == -1) {
            showCustomToast("Select Gender", false);
            return;
        }

        RadioButton rb = findViewById(selectedId);
        String gender = rb.getText().toString();

        showCustomToast("Welcome " + name + "\nEmail: " + email + "\nGender: " + gender, true);
    }

    // Custom Toast created programmatically
    private void showCustomToast(String message, boolean success) {

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(30, 20, 30, 20);
        layout.setGravity(Gravity.CENTER_VERTICAL);

        layout.setBackgroundColor(success ? Color.parseColor("#4CAF50")
                : Color.parseColor("#F44336"));

        ImageView icon = new ImageView(this);
        icon.setImageResource(success ?
                android.R.drawable.checkbox_on_background :
                android.R.drawable.ic_delete);

        TextView text = new TextView(this);
        text.setText(message);
        text.setTextColor(Color.WHITE);
        text.setPadding(20, 0, 0, 0);

        layout.addView(icon);
        layout.addView(text);

        Toast toast = new Toast(this);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
