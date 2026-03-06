package com.example.p22;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    EditText name, email, phone, password;
    Button insert, view, update, delete;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etPhone);
        password = findViewById(R.id.etPassword);

        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);

        db = new DBHelper(this);

        // INSERT BUTTON CLICK LISTENER
        insert.setOnClickListener(v -> {
            String nameStr = name.getText().toString().trim();
            String emailStr = email.getText().toString().trim();
            String phoneStr = phone.getText().toString().trim();
            String passwordStr = password.getText().toString().trim();

            if (nameStr.isEmpty() || emailStr.isEmpty() || phoneStr.isEmpty() || passwordStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                boolean result = db.insertData(nameStr, emailStr, phoneStr, passwordStr);

                if (result) {
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                    password.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Insert Failed", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // VIEW BUTTON CLICK LISTENER
        view.setOnClickListener(v -> {
            Cursor res = db.getData();

            if (res.getCount() == 0) {
                showMessage("Error", "No Data Found");
                res.close();
                return;
            }

            StringBuffer buffer = new StringBuffer();

            while (res.moveToNext()) {
                buffer.append("ID: " + res.getString(0) + "\n");
                buffer.append("Name: " + res.getString(1) + "\n");
                buffer.append("Email: " + res.getString(2) + "\n");
                buffer.append("Phone: " + res.getString(3) + "\n\n");
            }

            res.close();
            showMessage("All Records", buffer.toString());
        });

        // UPDATE BUTTON CLICK LISTENER
        update.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Update Record");
            builder.setCancelable(true);

            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(50, 40, 50, 40);

            EditText idInput = new EditText(MainActivity.this);
            idInput.setHint("Enter ID to Update");
            layout.addView(idInput);

            builder.setView(layout);

            builder.setPositiveButton("Update", (dialog, which) -> {
                String id = idInput.getText().toString().trim();
                String nameStr = name.getText().toString().trim();
                String emailStr = email.getText().toString().trim();
                String phoneStr = phone.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();

                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (nameStr.isEmpty() || emailStr.isEmpty() || phoneStr.isEmpty() || passwordStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    boolean result = db.updateData(id, nameStr, emailStr, phoneStr, passwordStr);

                    if (result) {
                        Toast.makeText(MainActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        email.setText("");
                        phone.setText("");
                        password.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
            builder.show();
        });

        // DELETE BUTTON CLICK LISTENER
        delete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Delete Record");
            builder.setCancelable(true);

            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(50, 40, 50, 40);

            EditText idInput = new EditText(MainActivity.this);
            idInput.setHint("Enter ID to Delete");
            layout.addView(idInput);

            builder.setView(layout);

            builder.setPositiveButton("Delete", (dialog, which) -> {
                String id = idInput.getText().toString().trim();

                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int result = db.deleteData(id);

                    if (result > 0) {
                        Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Delete Failed - ID not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
