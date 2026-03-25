package com.example.p24;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewContactsActivity extends AppCompatActivity {

    TextView display;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        display = findViewById(R.id.display);

        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM contacts", null);

        StringBuilder data = new StringBuilder();

        while (cursor.moveToNext()) {
            data.append("ID: ").append(cursor.getInt(0)).append("\n");
            data.append("Name: ").append(cursor.getString(1)).append("\n");
            data.append("Phone: ").append(cursor.getString(2)).append("\n\n");
        }

        display.setText(data.toString());
    }
}