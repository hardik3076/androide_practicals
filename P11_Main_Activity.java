package com.example.p11_1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ListView listView;

    String[] bookTitles = {"Android", "Java", "Python", "DSA"};
    int[] bookImages = {
            R.drawable.img_1,
            R.drawable.img_1,
            R.drawable.img_1,
            R.drawable.img_1
    };

    String[] borrowers = {
            "Amit - 01 Aug",
            "Neha - 03 Aug",
            "Rahul - 05 Aug",
            "Sneha - 07 Aug"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridViewBooks);
        listView = findViewById(R.id.listViewBorrowers);

        gridView.setAdapter(new BookAdapter());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        borrowers);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this,
                        "Borrower: " + borrowers[position],
                        Toast.LENGTH_SHORT).show()
        );
    }

    class BookAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return bookTitles.length;
        }

        @Override
        public Object getItem(int position) {
            return bookTitles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater()
                        .inflate(R.layout.grid_item, parent, false);
            }

            ImageView imageView = convertView.findViewById(R.id.bookImage);
            TextView textView = convertView.findViewById(R.id.bookTitle);

            imageView.setImageResource(bookImages[position]);
            textView.setText(bookTitles[position]);

            return convertView;
        }
    }
}


//add 2 images in it 
