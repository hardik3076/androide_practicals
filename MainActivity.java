package com.example.p24;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, phone, email, search;
    Button addBtn, viewBtn, searchBtn;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        search = findViewById(R.id.search);

        addBtn = findViewById(R.id.addBtn);
        viewBtn = findViewById(R.id.viewBtn);
        searchBtn = findViewById(R.id.searchBtn);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        // INSERT (Add Contact)
        addBtn.setOnClickListener(v -> {
            if (name.getText().toString().isEmpty() ||
                    phone.getText().toString().isEmpty()) {
                Toast.makeText(this, "Fill required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues cv = new ContentValues();
            cv.put("name", name.getText().toString());
            cv.put("phone", phone.getText().toString());
            cv.put("email", email.getText().toString());

            db.insert("contacts", null, cv);
            Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
        });

        // SEARCH (Q3)
        searchBtn.setOnClickListener(v -> {
            String key = search.getText().toString();

            Cursor cursor = db.rawQuery(
                    "SELECT * FROM contacts WHERE name LIKE ? OR phone LIKE ?",
                    new String[]{"%" + key + "%", "%" + key + "%"}
            );

            StringBuilder result = new StringBuilder();

            while (cursor.moveToNext()) {
                result.append(cursor.getString(1)).append(" - ")
                        .append(cursor.getString(2)).append("\n");
            }

            Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
        });

        // NAVIGATION (Q4)
        viewBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewContactsActivity.class);
            startActivity(intent);
        });
    }
}